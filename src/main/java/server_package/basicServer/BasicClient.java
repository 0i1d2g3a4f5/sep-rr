package server_package.basicServer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.*;
import server_package.Client;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @author Sarp Cagin Erdogan
 */
public class BasicClient extends Client {
    private BasicMessageProcessor messageProcessor;
    private int figure = 7;

    //private
    BasicServer server;
    private String name;
    //private
    int id;
    private Socket socket;
    //private
    String group;

    //private
    boolean isReady, isListening, isNamed, isAI;

    public BasicClient(BasicServer server, int id, Socket socket){
        this.server=server;
        this.id=id;
        this.socket=socket;
        this.isNamed=false;
        this.isListening=false;
        this.isReady=false;
        this.messageProcessor=new BasicMessageProcessor(this);
    }

    public BasicServer getServer() {
        return server;
    }

    public void setServer(BasicServer server) {
        this.server = server;
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while(isListening && !server.isTerminated  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    counter++;
                    if(counter>=500){
                        sendSelf(new MessageAlive());
                        counter=0;
                    }
                    if (isListening && socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();
                        //Message message = new Message(jsonObject);
                        System.out.println("RECEIVED BY " + id + " :: " + jsonObject.toString());
                        messageProcessor.process(jsonObject);

                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                } catch (ClientNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    };
    public void listen(){
        isListening=true;
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
    }
    void sendProtocolCheck(){
        sendSelf(new MessageHelloClient("Version 0.1"));
    }
    void disconnect(){
        if(isListening) {
            isListening = false;
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int getId(){
        return id;
    }
    void removeClientFromList(){
        server.currentClients--;
        server.clientList.remove(this);
    }
    void shutDownClient(){
        disconnect();
        removeClientFromList();
    }
    void sendSingle(BasicClient client, Message message){
        try {
            OutputStream outputStream = client.socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            System.out.println("SENT TO " + client.id + " :: " + message.toString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSelf(Message message) {
        sendSingle(this, message);
    }

    public void sendAll(Message message) {
        for(BasicClient client : server.clientList){
            if(client.isListening)
                sendSingle(client, message);
        }
    }

    void sendList(List<BasicClient> clients, Message message) {
        for (BasicClient client : clients) {
            sendSingle(client, message);
        }
    }
    public void checkValues(String name, int figure){
        boolean available = true;
        for(BasicClient client : server.clientList){
            if(client.isNamed && client.figure==figure){
                available=false;
                break;
            }
        }
        if(available){
            isNamed=true;
            this.name=name;
            this.figure=figure;
            sendAll(new MessagePlayerAdded(id, this.name, this.figure));
        }
        else{
            sendSelf(new MessageError("ERROR :: Figure already taken."));
        }
    }
    public void sendPreviousInfo(){
        for(BasicClient client : server.clientList){
            if(client.isNamed && client.id!=this.id){
                System.out.println(client.id + client.name + client.figure +  client.isReady);
                sendSelf(new MessagePlayerAdded(client.id, client.name, client.figure));
                sendSelf(new MessagePlayerStatus(client.id, client.isReady));
            }
        }
    }
}
