package client_package.sentient;

import client_package.Client;
import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.Position;
import javafx.geometry.Pos;
import messages.*;
import org.apache.log4j.Logger;
import utility.GlobalParameters;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SentientClient extends Client {
    HashMap<Integer, Position> nextCheckPoints;
    ArrayList<Position> startingPoints = new ArrayList<>();
    int currentGoal;
    SentientBehaviour sentientBehaviour;

    int lastTriedFigure = 1;
    SentientController sentientController;
    public SentientClient(SentientController sentientController){
        this.currentGoal=1;
        nextCheckPoints = new HashMap<>();
        this.sentientController = sentientController;
        this.sentientBehaviour = new SentientBehaviour(this);

        getLogger().info("Created new SentientClient for this controller.");
    }



    @Override
    public void sendSelf(Message temp) {
        toSendList.add(temp);
        while(toSendList.size()>0){
            Message message = toSendList.get(0);
            String string = message.toJSON().toString();
            string+="\n";
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(getSocket().getOutputStream())));
                bufferedWriter.write(string);
                bufferedWriter.flush();
                getLogger().info("Sent message: " + string.substring(0, string.length()-2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            toSendList.remove(0);
        }
    }


    @Override
    public void listen() {
        setIsListening(true);
        Thread listenerThread = new Thread(new Runnable(){
            @Override
            public void run() {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (isListening) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        while(bufferedReader.ready()) {
                            String received = bufferedReader.readLine();
                            receivedMessages.add(received);
                            getLogger().info("Recevied message: " + received);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    handleReceivedMessages();


                }
            }
        });
        listenerThread.start();

    }
    public void handleReceivedMessages(){
        while (receivedMessages.size()>0){
            /*String text ="\nCurrent messages:\n";
            for(int i=0; i<receivedMessages.size(); i++){
                text+=(i + " : " + receivedMessages.get(i) + "\n");
            }
            getLogger().info(text);*/
            String string = receivedMessages.get(0);
            JsonObject jsonObject = new Gson().fromJson(string, JsonObject.class);
            process(jsonObject, this);
            receivedMessages.remove(0);
        }
    }
    public void handleReady(boolean ready, int id){
        if(ready){
            for(int i=0; i<clientList.size(); i++){
                Client temp = clientList.get(i);
                if(temp.getId()==id){
                    temp.setIsReady(ready);
                    temp.setPlayer(new CPlayer(temp, false));
                    playerList.add(temp);
                    break;
                }
            }
        }
        else{
            for(int i=0; i<playerList.size(); i++){
                Client temp = playerList.get(i);
                if(temp.getId()==id){
                    temp.setIsReady(ready);
                    playerList.remove(ready);
                    break;
                }
            }
            for(int i=0; i<clientList.size(); i++){
                Client temp = clientList.get(i);
                if(temp.getId()==id){
                    temp.setIsReady(ready);
                    temp.setPlayer(null);
                    break;
                }
            }
        }
    }



    @Override
    public void process(JsonObject jsonObject, Client client) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
                Message message = new MessageFactory().createMessage(messageType, jsonObject);
                try {
                    message.activateMessageInAIFrontend((SentientClient) client);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClientNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }


    public Logger getLogger() {
        return sentientController.getLogger();
    }
    public String getProtocolVersion() {
        return GlobalParameters.PROTOCOL_VERSION;
    }

    public SentientBehaviour getSentientBehaviour() {
        return sentientBehaviour;
    }
}
