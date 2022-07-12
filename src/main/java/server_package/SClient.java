package server_package;

import com.google.gson.JsonObject;
import gamelogic.Player;
import newmessages.*;
import server_package.advancedServer.AdvancedSClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    protected boolean isReady, isListening, isNamed, isAI, isBasic;


    public SClient(Server server, int id, Socket socket, boolean isBasic){
        setServer(server);
        setId(id);
        setSocket(socket);
        setBasic(isBasic);

    }
    public SClient(){

    }

    public void sendSingle(SClient sClient, Message temp){
        addToSendList(temp);
        while (toSendList.size() > 0){
            Message message = toSendList.get(0);
                synchronized (message) {


                try {
                    OutputStream outputStream = sClient.socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
                    String toSend = message.toJSON().toString().replaceAll("\n", "").trim() + "\n";

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));

                    writer.write(toSend);
                    writer.write("\n");
                    writer.flush();


                    /*
                    char[] arr = toSend.toCharArray();
                    String print = "";
                    int count = 0;
                    for (char c : arr) {
                        dataOutputStream.writeInt((int) c);
                        print += c;
                        count++;
                        dataOutputStream.flush();
                    }

                     */

                    //System.out.println("ToSend length: " + toSend.length());
                    System.out.println("SENT: " + toSend);
                    Server.serverLogger.info("Listener sent" + toSend);

                    toSendList.remove(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (toSendList.size()>0) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
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

    void sendList(List<AdvancedSClient> clients, Message message) {
        for (AdvancedSClient client : clients) {
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
    public void process(JsonObject jsonObject, boolean isBasic) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInBackend(this, isBasic);
    }
}
