package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
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
    int listIndex;

    Server server;
    String name;
    int id;
    String listName;
    String lobbyName;
    Socket socket;
    String group;

    boolean response, isTerminated, isNamed, isAI;
    public Client(Server server, Socket socket, int id){
        this.server=server;
        this.socket=socket;
        this.id=id;
        this.isNamed=false;
        this.isTerminated=false;
        this.messageProcessor=new MessageProcessor(this);
        Thread shutDown = new Thread(shutDownActions);
        shutDown.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutDown);
    }
    Runnable shutDownActions = new Runnable() {
        @Override
        public void run() {
            disconnect();
        }
    };
    //only for testing
     Client() {

    }

    public int getClientID(){
        return this.id;
    }
    void printIndex(){
        System.out.println("Name: "+name+" | ListIndex: " + listIndex);
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            //System.out.println("Listener of client " + id + " started.");
            int counter = 0;
            while(!isTerminated && !server.isTerminated  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    counter++;
                    if(counter>=50){
                        sendSelf(new MessageAlive());
                        response=false;
                        Thread thread = new Thread(checkConnection);
                        thread.setDaemon(true);
                        thread.start();

                        counter=0;
                    }
                    if (!isTerminated && socket.getInputStream().available() > 0) {
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
    Runnable checkConnection = new Runnable() {
        @Override
        public void run() {
            {

                try {
                    TimeUnit.MILLISECONDS.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(!isTerminated) {
                    if (!response) {
                        System.out.println("Got no response and disconnected.");
                        disconnect();
                    }
                }
            }

        }
    };
    void listen(){
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
        listName="ID: " + String.valueOf(id) + " | " + "Unnamed";

    }
    void disconnect(){
        if(!isTerminated) {
            isTerminated = true;
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listName = "ID: " + String.valueOf(id) + " | Figure: " + String.valueOf(figure) + " | Name: " + name + " | DISCONNECTED";
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Index", new JsonPrimitive(listIndex));
            jsonObject.add("Text", new JsonPrimitive(listName));
            server.application.addTask(new Task("UpdateList", jsonObject));
        }
    }
    void shutDownClient(){
            disconnect();
            if(!isTerminated) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("Index", new JsonPrimitive(listIndex));
                server.application.addTask(new Task("RemoveFromList", jsonObject));
                server.currentClients--;
                server.clientList.remove(this);
            }

    }
    void sendProtocolCheck(){
        MessageHelloClient messageProtocol = new MessageHelloClient("Version 0.1");
        sendSelf(messageProtocol);
    }
    public void checkName(String string, int figure){
        boolean available=true;
        for(Client client : server.clientList){
            if(!client.equals(this) && client.isNamed && client.name.equals(string)){
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
            listName = "ID: " + String.valueOf(id) + " | Figure: " + String.valueOf(figure) + " | Name: " + name;
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Index", new JsonPrimitive(listIndex));
            jsonObject.add("Text", new JsonPrimitive(listName));
            server.application.addTask(new Task("UpdateList", jsonObject));
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
            if(!client.isTerminated)
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
