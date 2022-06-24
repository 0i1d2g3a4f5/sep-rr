package server_package.basicServer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import newmessages.*;
import server_package.Client;
import server_package.MessageProcessor;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
/**
 * @author Sarp Cagin Erdogan
 */
public class BasicClient extends Client {

    public BasicClient(BasicServer server, int id, Socket socket){
        super(server, id, socket, true);
        setNamed(false);
        setListening(false);
        setReady(false);
        setMessageProcessor(new MessageProcessor(this, true));
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while(getIsListening() && !getServer().getIsTerminated()  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    String hahaha = "";
                    while (socket.getInputStream().available() > 0) {
                        char a = (char)socket.getInputStream().read();
                        hahaha+=String.valueOf(a);
                    }
                    if(!hahaha.equals("")){
                        JsonObject jsonObject = JsonParser.parseString(hahaha).getAsJsonObject();
                        System.out.println("RECEIVED :: " + jsonObject);
                        try {
                            messageProcessor.process(jsonObject);
                        } catch (ClientNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
    @Override
    public void listen(){
        setListening(true);
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
    }
    @Override
    public void sendProtocolCheck(){
        sendSelf(new MessageHelloClient("Version 0.1"));
    }
    @Override
    public void disconnect(){
        if(getIsListening()) {
            setListening(false);
            try {
                getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void removeClientFromList(){
        getServer().setCurrentClients(getServer().getCurrentClients()-1);
        getServer().getClientList().remove(this);
    }
    @Override
    public void shutDownClient(){
        disconnect();
        removeClientFromList();
    }
    @Override
    public void checkValues(String name, int figure){
        boolean available = true;
        for(Client client : getServer().getClientList()){
            if(client.getIsNamed() && client.getFigure()==figure){
                available=false;
                break;
            }
        }
        if(available){
            setNamed(true);
            setName(name);
            setFigure(figure);
            sendAll(new MessagePlayerAdded(getId(), getName(), getFigure()));
        }
        else{
            sendSelf(new MessageError("ERROR :: Figure already taken."));
        }
    }
    @Override
    public void sendPreviousInfo(){
        for(Client client : getServer().getClientList()){
            if(client.getIsNamed() && client.getId()!=this.getId()){
                System.out.println(client.getId() + client.getName() + client.getFigure() +  client.getIsReady());
                sendSelf(new MessagePlayerAdded(client.getId(), client.getName(), client.getFigure()));
                sendSelf(new MessagePlayerStatus(client.getId(), client.getIsReady()));
            }
        }
    }
}
