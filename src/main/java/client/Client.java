package client;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.Message;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client extends Thread{
    Application application;
    String name = "";
    int id;
    Socket socket;
    boolean isTerminated;
    MessageProcessor messageProcessor;
    Client(Application application){
        this.application=application;
        messageProcessor = new MessageProcessor(this);
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
                    throw new RuntimeException(e);
                } catch (IOException e) {
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

                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("Listening...");
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
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
        isTerminated = false;
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
        application.taskList.add(new Task("SwitchToName", ""));
        application.executeTasks();
    }
    void listen(){
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();

    }
    void disconnect(){

    }
    void sendSelf(Message message){
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
