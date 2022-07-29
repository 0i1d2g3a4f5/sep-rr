package server_package.basicServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.Game;
import messages.*;
import server_application.Task;
import server_application.TaskContent;
import server_application.TaskType;
import server_package.SClient;
import server_package.Server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicSClient extends SClient {

    /**
     * @param server
     * @param id
     * @param socket
     */
    public BasicSClient(BasicServer server, int id, Socket socket){
        super(server, id, socket, true);
        setNamed(false);
        setListening(false);
        setReady(false);
    }

    /**
     * Listener checks if there are messages in the input stream,
     *      if this is the case, they get added to our received messages
     */
    @Override
    public void listen() {
        setListening(true);
        Thread listenerThread = new Thread(new Runnable(){
            // When the listener is listening we check the input stream every 10 milliseconds for content
            @Override
            public void run() {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (isListening) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        while(bufferedReader.ready()) {
                            // If there is content in the input stream we add this content to our received messages
                            String received = bufferedReader.readLine();
                            receivedMessages.add(received);
                            Server.serverLogger.info("Received message: " + received);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    handleReceivedMessages();

                }
            }
        });
        listenerThread.start();

    }

    /**
     * If the amount of received messages is greater zero we want to remove the jsonObject in the 0th position
     */
    public void handleReceivedMessages(){
        while (receivedMessages.size()>0){
            String string = receivedMessages.get(0);
            JsonObject jsonObject = new Gson().fromJson(string, JsonObject.class);
            try {
                process(jsonObject);
            } catch (ClientNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            receivedMessages.remove(0);
        }
    }

    @Override
    public void sendProtocolCheck(){
        sendSelf(new MessageHelloClient(PROTOCOL_VERSION));
    }

    /**
     * Disconnect player and in return also disable listener for said player
     */
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
        getServer().getClientList().remove(this);
        // Check if there are any players on the player list
        for(int i = 0; i < Game.getInstance().getPlayerList().size(); i++){
            // Remove player off player list
            if(Game.getInstance().getPlayerList().get(i).getClient().getId() == this.getId()){
                Game.getInstance().getPlayerList().remove(i);
                break;
            }
        }
        server.getServerApplication().serverSelectionControllerVM.updateServerList();
        getServer().setCurrentClients(getServer().getCurrentClients()-1);
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
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        super.process(jsonObject);
    }

    /**
     * Setting of clients in game and selected figure in boolean values
     * @param name
     * @param figure
     */
    @Override
    public void checkValues(String name, int figure){
        boolean available = true;
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getFigure()==figure){
                available=false;
                break;
            }
        }
        if(available){
            setNamed(true);
            setName(name);
            setFigure(figure);
            getServer().getServerApplication().serverSelectionControllerVM.updateServerList();
            sendAll(new MessagePlayerAdded(getId(), getName(), getFigure()));
            Server.serverLogger.info("Client " + name + " | " + id + " joined the game");
            Game.getInstance().join(this);
        }
        else{
            sendSelf(new MessageError("Figure is already taken"));
            Server.serverLogger.error("Chosen figure already taken");
        }
    }

    @Override
    public void sendPreviousInfo(){
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getId()!=this.getId()){
                sendSelf(new MessagePlayerAdded(sClient.getId(), sClient.getName(), sClient.getFigure()));
                sendSelf(new MessagePlayerStatus(sClient.getId(), sClient.getIsReady()));
            }
        }
    }

}
