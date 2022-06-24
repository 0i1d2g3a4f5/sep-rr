package client_package;

import client_application.ClientApplication;
import newmessages.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public abstract class Client {
    protected List<Client> playerList;
    protected ClientApplication clientApplication;
    protected String group;
    protected String name = "";
    protected int figure, id;
    protected Socket socket;
    protected boolean isListening, isReady, isForList, isBasic;
    protected MessageProcessor messageProcessor;
    public Client(ClientApplication clientApplication, boolean isBasic){
        setClientApplication(clientApplication);
        setIsBasic(isBasic);
        setPlayerList(new ArrayList<>());
    }
    public Client(boolean isBasic){
        setIsBasic(isBasic);
    }

    public Client clientFromId(int inp){
        for(Client client : this.playerList){
            if(client.getId()==inp){
                return client;
            }
        }
        return null;
    }


    public void sendSelf(Message message){

        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            String toSend = message.toJSON().toString();
            String print = "";
            for(int i=0; i<toSend.length(); i++){
                print+=toSend.charAt(i);
                dataOutputStream.write((int )toSend.toCharArray()[i]);
                dataOutputStream.flush();
            }
            System.out.println(print);
            /*dataOutputStream.writeUTF(message.toJSON().toString());
            System.out.println("very important:" + message.toJSON().toString());
            dataOutputStream.flush();
            System.out.println("SENT :: " + message.toString());*/
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*GETTER SETTER
    *
    *
    *
    *
    *
    *
    *
    *
    */
    public List<Client> getPlayerList(){
        return this.playerList;
    }
    public void setPlayerList(List<Client> clientList){
        this.playerList=clientList;
    }

    public ClientApplication getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Socket getSocket(){
        return this.socket;
    }
    public void setSocket(Socket socket){
        this.socket=socket;
    }

    public boolean isListening() {
        return isListening;
    }

    public void setIsListening(boolean listening) {
        isListening = listening;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }

    public boolean isForList() {
        return isForList;
    }

    public void setIsForList(boolean forList) {
        isForList = forList;
    }

    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public void setIsBasic(boolean basic) {
        isBasic = basic;
    }
}
