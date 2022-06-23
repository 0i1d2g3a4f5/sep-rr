package server_package;

import gamelogic.Player;
import newmessages.Message;
import server_package.advancedServer.AdvancedClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public abstract class Client {
    protected MessageProcessor messageProcessor;
    protected int figure = 7;
    protected String group;
    protected Server server;
    protected Player player;
    protected int id;
    protected String name;
    protected Socket socket;
    protected boolean isReady, isListening, isNamed, isAI, isBasic;


    public Client(Server server, int id, Socket socket, boolean isBasic){
        setServer(server);
        setId(id);
        setSocket(socket);
        setBasic(isBasic);

    }
    public Client(){

    }

    void sendSingle(Client client, Message message){
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
        for(Client client : server.getClientList()){
            if(client.isListening)
                sendSingle(client, message);
        }
    }

    void sendList(List<AdvancedClient> clients, Message message) {
        for (AdvancedClient client : clients) {
            sendSingle(client, message);
        }
    }
    /* GETTER SETTER
    *
    *
    *
    *
    *
    *
    *
    *
    */
    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }


    public Server getServer(){
        return server;
    };

    public Player getPlayer() {
        return player;
    }

    public int getId(){ return id;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getGroup(){
        return group;
    }
    public void setGroup(String string){
        this.group=string;
    }
    public Socket getSocket(){
        return socket;
    }
    public MessageProcessor getMessageProcessor(){
        return messageProcessor;
    }
    public void setMessageProcessor(MessageProcessor messageProcessor){
        this.messageProcessor=messageProcessor;
    }


    public boolean getIsNamed() {
        return isNamed;
    }
    public void setNamed(boolean bool){
        this.isNamed=bool;
    }
    public boolean getIsListening(){
        return isListening;
    }
    public void setListening(boolean bool){
        this.isListening=bool;
    }
    public boolean getIsReady() {
        return isReady;
    }
    public void setReady(boolean bool){
        this.isReady=bool;

    }
    public boolean getIsAI() {
        return isAI;
    }
    public void setAI(boolean bool){
        this.isAI=bool;

    }


    public boolean isBasic() {
        return isBasic;
    }

    public void setBasic(boolean basic) {
        isBasic = basic;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract void listen();

    public abstract void sendProtocolCheck();

    public abstract void disconnect();

    public abstract void removeClientFromList();

    public abstract void shutDownClient();

    public abstract void checkValues(String name, int figure);

    public abstract void sendPreviousInfo();
}
