package server;

import messages.Message;
import messages.Serializer;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public class Client {
    MessageProcessor messageProcessor;
    Serializer serializer;

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
        this.serializer=new Serializer();
        this.messageProcessor = new MessageProcessor(this);
        this.isNamed=false;
        this.isTerminated=false;
    }
    public int getClientID(){
        return this.id;
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            while(!server.isTerminated && !isTerminated){
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
            dataOutputStream.writeUTF(serializer.serialize(message));
            dataOutputStream.flush();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void sendSelf(Client client, Message message) {
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
