package client_package.sentient;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import newmessages.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SentientClient extends Client {
    int lastTriedFigure = 1;
    SentientController sentientController;
    public SentientClient(SentientController sentientController){
        this.sentientController = sentientController;
        getLogger().info("Created new SentientClient for this controller.");
    }
    public void displayClientList(){
        String text = "\nCurrent opponents in lobby:\n";
        for(int i=0; i<clientList.size(); i++){
            Client client = clientList.get(i);
            text+=(client.getId() + " : \"" + client.getName() + "\" with figure " + client.getFigure());
            if(client.isReady()){
                text+=" is ready.\n";
            }
            else {
                text+=" is not ready.\n";
            }
        }
        getLogger().info(text);
    }
    public void displayPlayerList(){
        String text = "\nCurrent opponents in game:\n";
        for(int i=0; i<playerList.size(); i++){
            Client client = playerList.get(i);
            text+=(client.getId() + " : \"" + client.getName() + "\" with figure " + client.getFigure());
            if(client.isReady()){
                text+=" is ready.\n";
            }
            else {
                text+=" is not ready.\n";
            }
        }
        getLogger().info(text);
    }
    public void start(String name){
        int i = 1+ (int) ( Math.random() * 9999);
        String str = String.valueOf(i);
        setName(name+str);
        createSocket();
        listen();
    }
    public void start(String name, String string, int port, String group){
        int i = 1+ (int) ( Math.random() * 9999);
        String str = String.valueOf(i);
        setName(name+str);
        createSocket(string, port);
        listen();
        setGroup(group);
    }
    public boolean createSocket(){
        boolean result = false;
        String ip = scanIP();
        int port = scanPort();
        try {
            setSocket(new Socket(ip, port));
            result=true;
        } catch (UnknownHostException e) {
            getLogger().error("Couldn't create socket. Please retry.");
            createSocket();
            throw new RuntimeException(e);
        } catch (IOException e) {
            getLogger().error("Couldn't create socket. Please retry.");
            createSocket();
            throw new RuntimeException(e);
        }
        return result;
    }
    public String scanIP(){
        System.out.println("Please provide ip: ");
        Scanner scanner = new Scanner(System.in);
        String scannedIP = scanner.next();
        return scannedIP;
    }
    public int scanPort(){
        System.out.println("Please provide port: ");
        Scanner scanner = new Scanner(System.in);
        String scannedPort = scanner.next();
        int port = 0;
        try {
            port = Integer.valueOf(scannedPort);
        } catch (NumberFormatException numberFormatException){
            System.out.println("Port should be a number.");
            return scanPort();
        }
        return port;
    }
    public boolean createSocket(String ip, int port){
        boolean result = false;
        try {
            setSocket(new Socket(ip, port));
            result=true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public void sendSelf(Message temp) {
        toSendList.add(temp);
        handleToBeSent();
    }
    public void handleToBeSent(){
        while(toSendList.size()>0){
            Message message = toSendList.get(0);
            String string = message.toJSON().toString();
            string+="\n";
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(getSocket().getOutputStream())));
                bufferedWriter.write(string);
                bufferedWriter.flush();
                getLogger().info("Sent message: " + string);
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
    public void sayHelloToServer(){
        sendSelf(new MessageHelloServer(getGroup(), true, getProtocolVersion()));
    }
    public void handleReady(boolean ready, int id){
        if(ready){
            for(int i=0; i<clientList.size(); i++){
                Client temp = clientList.get(i);
                if(temp.getId()==id){
                    temp.setIsReady(ready);
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
                    break;
                }
            }
        }
    }
    public void sendOwnInfo(){
        sendSelf(new MessagePlayerValues(getName(), lastTriedFigure));

    }
    public void retryFigure(){
        if(lastTriedFigure<7){
            lastTriedFigure++;
            sendOwnInfo();
        }
        else{
            getLogger().error("No figures available :(");
        }
    }
    public void triggerGameStart(JsonObject jsonObject) throws IOException {
        Game.getInstance().setMap(new GameBoard(jsonObject));
        Game game = Game.getInstance();
        game.setClient(this);
        setGame(game);
    }
    public Logger getLogger() {
        return sentientController.getLogger();
    }
    public String getProtocolVersion() {
        return sentientController.getProtocolVersion();
    }
}
