package client;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.Message;
import newmessages.MessageAlive;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client extends Thread{
    String group;
    Application application;
    String name = "";
    int figure;
    int id;
    Socket socket;
    boolean isReconnecting = false;
    boolean isTerminated=true;
    MessageProcessor messageProcessor;
    HashSystem hashSystem;
    Client(Application application){
        this.application=application;
        messageProcessor = new MessageProcessor(this);
        hashSystem = new HashSystem(this);
    }
    Runnable shutDownActions = new Runnable() {
        @Override
        public void run() {

        }
    };
    void startClient(String ip, int port){
        Thread shutDown = new Thread(shutDownActions);
        shutDown.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutDown);

        Runnable socketCreation = new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, port);
                    socketCreationSuccessful();
                } catch (UnknownHostException e) {
                    application.addTask(new Task("FailedSocket", new JsonObject()));
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    application.addTask(new Task("FailedSocket", new JsonObject()));
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(socketCreation);
        thread.setDaemon(true);
        thread.start();



    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            while(!isTerminated){
                try {

                    TimeUnit.MILLISECONDS.sleep(100);
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
                        //System.out.println("RECEIVED: " + input);
                        messageProcessor.process(jsonObject);
                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
    void socketCreationSuccessful(){
        listen();
    }
    void listen(){
        isTerminated=false;
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();

    }
    void disconnect(){

    }
    public String getNewEncoded(String string){
        hashSystem.createNewHashes(string);
        return hashSystem.encodeString(string, hashSystem.hashes);
    }
    public String getOldEncoded(String string){
        return hashSystem.encodeString(string, hashSystem.oldHashes);
    }

    void sendSelf(Message message){

        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            //System.out.println("SENT: " + message.toJSON().toString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void shutDown(){
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
