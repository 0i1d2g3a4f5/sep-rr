package client;

import messages.Message;
import messages.Serializer;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client {
    Application application;
    MessageProcessor messageProcessor;
    Serializer serializer;
    String name = "";
    int id;
    Socket socket;
    boolean isTerminated;
    Client(Application application){
        this.application=application;
        this.serializer=new Serializer();
        this.messageProcessor = new MessageProcessor(this);
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
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isTerminated = false;
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();


    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            while(!isTerminated){
                try {
                    TimeUnit.MILLISECONDS.sleep(8);
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        messageProcessor.process(serializer.deserialize(input));
                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
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
            dataOutputStream.writeUTF(serializer.serialize(message));
            dataOutputStream.flush();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
