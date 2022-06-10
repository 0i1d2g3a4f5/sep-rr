package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.util.Pair;
import newmessages.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client {
    MessageProcessor messageProcessor;
    int figure = 7;

    Server server;
    String name;
    int id;
    String listName;
    String lobbyName;
    Socket socket;
    String group;
    boolean isTerminated, isNamed, isAI;
    Client(Server server, Socket socket, int id){

        this.server=server;
        this.socket=socket;
        this.id=id;
        this.isNamed=false;
        this.isTerminated=false;
        this.messageProcessor=new MessageProcessor(this);
    }
    public int getClientID(){
        return this.id;
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            System.out.println("Listener of client " + id + " started.");
            int counter = 0;
            while(!isTerminated && !server.isTerminated  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    counter++;
                    if(counter>=50){
                        sendSelf(new MessageAlive());
                        counter=0;
                    }
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
                        //System.out.println(id + " RECEIVED: " + input);
                        messageProcessor.process(jsonObject);

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
    void sendProtocolCheck(){
        MessageHelloClient messageProtocol = new MessageHelloClient("Version 0.1");
        sendSelf(messageProtocol);
    }
    public void checkName(String string, int figure){
        boolean available=true;
        for(Client client : server.clientList){
            if(!client.equals(this) && client.isNamed && client.name==string){
                available=false;
                sendSelf(new MessageNameUnavailable(string));
                break;
            }
        }
        if(available){
            checkFigure(string, figure);
        }

    }
    public void checkFigure(String string, int figure){
        boolean available=true;
        for(Client client : server.clientList){
            if(!client.equals(this) && client.isNamed && client.figure==figure){
                available=false;
                sendSelf(new MessageFigureUnavailable(figure));
                break;
            }
        }
        if(available){
            this.figure=figure;
            this.name=string;
            isNamed=true;
            sendSelf(new MessageValuesAccepted(name, figure));
            MessagePlayerAdded message = new MessagePlayerAdded(id, string, figure);
            sendAll(message);
            System.out.println("PLAYER ADDED :: " + message.toJSON());

        }
    }
    void sendSingle(Client client, Message message){
        try {
            OutputStream outputStream = client.socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            //System.out.println("SENT: " + message.toJSON().toString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void sendSelf(Message message) {
        sendSingle(this, message);
    }
    void sendAll(Message message) {
        for(Client client : server.clientList){
            sendSingle(client, message);
        }
    }
    void sendList(List<Client> clients, Message message) {
        for (Client client : clients) {
            sendSingle(client, message);
        }
    }
    void setReadyTrue(){

    }
    void setReadyFalse(){

    }





}
