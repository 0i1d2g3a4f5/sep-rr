package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.Message;
import newmessages.MessageProtocol;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client {

    Server server;
    String name = "";
    int id;
    String listName;
    String lobbyName;
    Socket socket;
    boolean isTerminated, isNamed;
    Client(Server server, Socket socket, int id){
        this.server=server;
        this.socket=socket;
        this.id=id;
        this.isNamed=false;
        this.isTerminated=false;
    }
    public int getClientID(){
        return this.id;
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            System.out.println("Listener of client " + id + " started.");
            while(!isTerminated && !server.isTerminated  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println(id + " is listening...");
                    if (socket.getInputStream().available() > 0) {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String input = dataInputStream.readUTF();
                        JsonObject jsonObject = JsonParser.parseString(input).getAsJsonObject();

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
        MessageProtocol messageProtocol = new MessageProtocol("Version 0.1");
        System.out.println(messageProtocol.protocol);
        sendSelf(messageProtocol);
    }
    public void checkName(String string) throws IOException {
        if(!isNamed && string.equals("")){
            System.out.println("HANDLE BLANK");
        }
        else if(!isNamed){
            boolean available = true;
            int ownerid=0;
            for(Client client : server.clientList){
                if(client.isNamed && client.name.equals(string)){
                    ownerid = client.id;
                    available=false;
                }
            }
            if(available){
                System.out.println("HANDLE AVAILABLE");
            }
            else{
                System.out.println("HANDLE UNAVAILABLE");
            }
        }

    }
    void sendSingle(Client client, Message message){
        try {
            OutputStream outputStream = client.socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            System.out.println("AAAAAAAAAAAAAAAAAAAAA");
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
