package client_package;

import client_application.ClientApplication;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.ThisPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import gamelogic.Color;
import newmessages.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public abstract class Client {
    protected ArrayList<Message> toSendList = new ArrayList<>();
    public void addToSendList(Message message){
        toSendList.add(message);
    }

    protected List<Client> clientList;
    //TODO playerlist as filter for clients that are playing
    protected List<Client> playerList;
    protected ClientApplication clientApplication;

    protected ThisPlayer player;
    protected Game game;

    {
        try {
            game = Game.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String group;
    protected String name = "";
    protected int figure, id;
    protected Socket socket;
    protected boolean isListening, isReady, isForList, isBasic;
    public Client(){
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

    public Client(ClientApplication clientApplication, boolean isBasic){
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


    public void sendSelf(Message temp){
        addToSendList(temp);
        Message message = toSendList.get(0);

        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
            String toSend = message.toJSON().toString().replaceAll("\n","").trim() + "\n";
            char[] arr = toSend.toCharArray();
            String print = "";
            for (char c:arr) {
                dataOutputStream.writeInt((int)c);
                print+=c;
            }
            dataOutputStream.flush();
            System.out.println("SENT: " + print);
            toSendList.remove(message);
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
            try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            while(isListening){


                    TimeUnit.MILLISECONDS.sleep(100);
                    String inputString = "";
                    boolean isEnded = false;
                    int i=0;
                    if(dataInputStream.available()>0)
                    System.out.println("waiting Chars: "+ dataInputStream.available());

                    while (!isEnded && dataInputStream.available() > 0) {
                        int in = dataInputStream.readInt();
                       // System.out.println(in);

                        char a = (char)in;
                        //TODO possible reason for fail

                        if((int) a == 10){
                            isEnded=true;
                        }


                        inputString+=String.valueOf(a);
                    }
                    if (inputString.length()>0)
                    System.out.println("message length: "+inputString.length());
                    if(!inputString.equals("")){
                        isEnded=false;
                        System.out.println("RECEIVED: " + inputString);
                        JsonObject jsonObject =  new Gson().fromJson(inputString, JsonObject.class);
                        process(jsonObject);
                    }
                }


            }catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
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
