package client_package;

import client_application.ClientApplication;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.ThisPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import gamelogic.Color;
import newmessages.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public abstract class Client {

    protected List<Client> clientList;
    //TODO playerlist as filter for clients that are playing
    protected List<Client> playerList;
    protected ClientApplication clientApplication;

    protected ThisPlayer player;
    protected Game game = Game.getInstance();
    protected String group;
    protected String name = "";
    protected int figure, id;
    protected Socket socket;
    protected boolean isListening, isReady, isForList, isBasic;
    public Client() throws IOException {
    }

    public Color getRoboColor(){
        switch (figure){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.ORANGE;

            case 5:
                return Color.PURPLE;

            case 6:
                return Color.YELLOW;

            default:throw new IllegalArgumentException();
        }
    }

    public Client(ClientApplication clientApplication, boolean isBasic) throws IOException {
        setClientApplication(clientApplication);
        setIsBasic(isBasic);
        setClientList(new ArrayList<>());
    }



    public Client clientFromId(int inp){
        for(Client client : this.clientList){
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
            String toSend = message.toJSON().toString()+"\n";
            String print = "";
            for(int i=0; i<toSend.length(); i++){
                print+=toSend.charAt(i);
                dataOutputStream.write((int)toSend.toCharArray()[i]);
                dataOutputStream.flush();
            }
            System.out.println("SENT: " + print);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Client> playerList) {
        this.playerList = playerList;
    }

    public void setPlayer(ThisPlayer player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void listen();
    public abstract void process(JsonObject jsonObject);
    protected Runnable basicListener = new Runnable() {
        @Override
        public void run() {
            while(isListening){
                try {

                    TimeUnit.MILLISECONDS.sleep(100);
                    String hahaha = "";
                    boolean isEnded = false;
                    int i=0;
                    while (!isEnded && socket.getInputStream().available() > 0) {
                        char a = (char)socket.getInputStream().read();
                        if((int) a == 10){
                            isEnded=true;
                        }
                        hahaha+=String.valueOf(a);
                    }
                    if(!hahaha.equals("")){
                        isEnded=false;
                        System.out.println("RECEIVED: " + hahaha);
                        JsonObject jsonObject = JsonParser.parseString(hahaha).getAsJsonObject();
                        process(jsonObject);
                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };



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

    public Game getGame() {
        return game;
    }

    public List<Client> getClientList(){
        return this.clientList;
    }
    public void setClientList(List<Client> clientList){
        this.clientList =clientList;
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


    public boolean isBasic() {
        return isBasic;
    }

    public void setIsBasic(boolean basic) {
        isBasic = basic;
    }


    public ThisPlayer getPlayer() {
      return player;
    }
}
