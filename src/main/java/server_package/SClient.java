package server_package;

import com.google.gson.JsonObject;
import gamelogic.Player;
import messages.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Sarp Cagin Erdogan
 */

public abstract class SClient {
    protected ArrayList<Message> toSendList = new ArrayList<>();
    public void addToSendList(Message message){
        toSendList.add(message);
    }
    protected int figure = 7;
    protected String group;
    protected Server server;
    protected Player player;
    protected int id;
    protected String name;
    protected Socket socket;
    protected ArrayList<String> receivedMessages = new ArrayList<>();
    protected boolean isReady, isListening, isNamed, isAI, isBasic;

    public SClient(Server server, int id, Socket socket, boolean isBasic){
        setServer(server);
        setId(id);
        setSocket(socket);
        setBasic(isBasic);
    }

    public SClient(){}

    public void sendSingle(SClient sClient, Message temp) {
        toSendList.add(temp);
        handleToBeSent(sClient);
    }

    /**
     * Create message from send list and send
     * @param sClient
     */
    public void handleToBeSent(SClient sClient){
        // If there is content on the toSendList we take the content from the 0th position and add it as a new message
        while(toSendList.size()>0){
            Message message = toSendList.get(0);
            String string = message.toJSON().toString();
            string+="\n";
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(sClient.getSocket().getOutputStream())));
                bufferedWriter.write(string);
                bufferedWriter.flush();
                Server.serverLogger.info("Sent message: " + string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            toSendList.remove(0);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void sendSelf(Message message) {
        sendSingle(this, message);
    }

    public void sendAll(Message message) {
        for(SClient sClient : server.getClientList()){
            if(sClient.isListening)
                sendSingle(sClient, message);
        }
    }

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
    public void setReady(boolean bool){this.isReady=bool;}
    public boolean getIsAI() {
        return isAI;
    }
    public void setAI(boolean bool){this.isAI=bool;}

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

    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInBackend(this);
    }
}
