package server_package.basicServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.Game;
import newmessages.*;
import server_package.SClient;
import server_package.Server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicSClient extends SClient {

    public BasicSClient(BasicServer server, int id, Socket socket){
        super(server, id, socket, true);
        setNamed(false);
        setListening(false);
        setReady(false);
    }
    /*Runnable listener = new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while(getIsListening() && !getServer().getIsTerminated()  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    counter++;
                    if(counter>=500){
                        sendSelf(new MessageAlive());
                        counter=0;
                    }
                    String inputString = "";
                    boolean isEnded = false;
                    int i=0;
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    DataInputStream dataInputStream= new DataInputStream(bufferedInputStream);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

                    int readChars =dataInputStream.available();

                    while(!isEnded && readChars>0){
                        String input = reader.readLine();
                        if(input.equals("\n" )|| input.equals("")){
                            isEnded = true;
                        }

                        else
                            inputString += input;
                        readChars--;
                    }

                    if(inputString !="")
                    *//*
                    while (!isEnded && dataInputStream.available() > 0) {
                        char a = (char)dataInputStream.readInt();
                        if((int) a == 10){
                            isEnded=true;
                        }
                        inputString+=String.valueOf(a);
                    }

                     *//*
                    if(!inputString.equals("")){

                            String[] strings = inputString.split("\n");
                            for (String string :strings
                            ) {
                                Server.serverLogger.info("Listener received" + inputString);
                                JsonObject jsonObject =  new Gson().fromJson(string, JsonObject.class);
                                process(jsonObject, true);
                            }

                        isEnded=false;

                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                } catch (ClientNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
    @Override
    public void listen(){
        setListening(true);
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
    }*/
    @Override
    public void listen() {
        setListening(true);
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
                            Server.serverLogger.info("Recevied message: " + received);
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
            try {
                process(jsonObject);
            } catch (ClientNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            receivedMessages.remove(0);
        }
    }
    @Override
    public void sendProtocolCheck(){
        sendSelf(new MessageHelloClient(PROTOCOL_VERSION));
    }
    @Override
    public void disconnect(){
        if(getIsListening()) {
            setListening(false);
            try {
                getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void removeClientFromList(){
        getServer().setCurrentClients(getServer().getCurrentClients()-1);
        getServer().getClientList().remove(this);
    }
    @Override
    public void shutDownClient(){
        disconnect();
        removeClientFromList();
    }

    @Override
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        super.process(jsonObject);
    }

    @Override
    public void checkValues(String name, int figure){
        boolean available = true;
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getFigure()==figure){
                available=false;
                break;
            }
        }
        if(available){
            setNamed(true);
            setName(name);
            setFigure(figure);
            sendAll(new MessagePlayerAdded(getId(), getName(), getFigure()));
            Server.serverLogger.info("Client " + name + " | " + id + " joined the game");
            Game.getInstance().join(this);
        }
        else{
            sendSelf(new MessageError("Figure is already taken"));
            Server.serverLogger.error("Chosen figure already taken");
        }
    }
    @Override
    public void sendPreviousInfo(){
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getId()!=this.getId()){
                sendSelf(new MessagePlayerAdded(sClient.getId(), sClient.getName(), sClient.getFigure()));
                sendSelf(new MessagePlayerStatus(sClient.getId(), sClient.getIsReady()));
            }
        }
    }
}
