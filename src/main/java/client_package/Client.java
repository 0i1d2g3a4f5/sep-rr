package client_package;

import client_application.ClientApplication;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.CPlayer;
import com.google.gson.JsonObject;
import gamelogic.Color;
import gamelogic.Position;
import javafx.application.Platform;
import messages.Message;
import messages.MessageConnectionUpdate;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class Client{

    public static Logger clientLogger = Logger.getLogger("Client");
    protected ArrayList<String> receivedMessages = new ArrayList<>();

    protected ArrayList<Message> toSendList = new ArrayList<>();
    public void addToSendList(Message message){
        toSendList.add(message);
    }
    protected ArrayList<Client> clientList = new ArrayList<>();

    protected ArrayList<Client> playerList = new ArrayList<>();
    protected ClientApplication clientApplication;

    protected CPlayer player;
    protected Game game;
    protected ArrayList<Position> highSlumber = new ArrayList<>();

    public ArrayList<Position> getHighSlumber() {
        return highSlumber;
    }

    public void setHighSlumber(ArrayList<Position> highSlumber) {
        this.highSlumber = highSlumber;
    }

    {
        try {
            game = Game.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String group;
    protected String name = "";
    protected int figure, id=-1;
    protected Socket socket;
    protected boolean isListening, isReady, isForList;
    public Client(){
    }
    public Client(int id, String name, int figure){
        setId(id);
        setName(name);
        setFigure(figure);
    }
    public Client(Game game, int id, int figure, String name){
        setGame(game);
        setId(id);
        setName(name);
        setFigure(figure);
    }

    public Color getRoboColor(){
        switch (figure){
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
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

    public Client(ClientApplication clientApplication){
        setClientApplication(clientApplication);
        setClientList(new ArrayList<>());
        game.setClient(this);
    }



    public Client playerFromId(int inp){
        displayPlayerList();
        for(Client client : this.playerList){
            if(client.getId()==inp){
                return client;
            }
        }
        return null;
    }
    public Client clientFromId(int inp){
        displayClientList();
        for(Client client : this.clientList){
            if(client.getId()==inp){
                return client;
            }
        }
        return null;
    }
    public void disconnect(){
        sendSelf(new MessageConnectionUpdate(getId(), false, "Remove"));
        Platform.exit();
    }
    public void displayClientList(){
        String text = "\nCurrent opponents in lobby:\n";
        for(int i=0; i<clientList.size(); i++){
            Client client = clientList.get(i);
            text+=(client.getId() + " : \"" + client.getName() + "\" with figure " + client.getFigure());
            if(client.isReady()){
                text+=" is ready.\n";
            }
            else {
                text+=" is not ready.\n";
            }
        }
        Client.clientLogger.info(text);
    }
    public void displayPlayerList(){
        String text = "\nCurrent opponents in game:\n";
        for(int i=0; i<playerList.size(); i++){
            Client client = playerList.get(i);
            text+=(client.getId() + " : \"" + client.getName() + "\" with figure " + client.getFigure());
            if(client.isReady()){
                text+=" is ready.\n";
            }
            else {
                text+=" is not ready.\n";
            }
        }
        Client.clientLogger.info(text);
    }

    public void sendSelf(Message temp){

    }

    public List<Client> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Client> playerList) {
        this.playerList = playerList;
    }

    public void setPlayer(CPlayer player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void listen(){

    }
    public void process(JsonObject jsonObject, Client client){

    }
    public void handleReady(boolean ready, int id){
        if(ready){
            for(int i=0; i<clientList.size(); i++){
                if(clientList.get(i).getId()==id){
                    clientList.get(i).setIsReady(ready);
                    playerList.add(clientList.get(i));
                    clientList.get(i).setPlayer(new CPlayer(clientList.get(i), false));
                    break;
                }
            }
        }
        else{
            for(int i=0; i<playerList.size(); i++){
                if(playerList.get(i).getId()==id){
                    playerList.remove(i);
                    break;
                }
            }
            for(int i=0; i<clientList.size(); i++){
                if(clientList.get(i).getId()==id){
                    clientList.get(i).setIsReady(ready);
                    clientList.get(i).setPlayer(null);
                    break;
                }
            }
        }
    }
    //TBD
    /*protected Runnable basicListener = new Runnable() {
        @Override
        public void run() {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                DataInputStream dataInputStream= new DataInputStream(bufferedInputStream);
                BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

                while(isListening){

                    TimeUnit.MILLISECONDS.sleep(10);
                    String inputString = "";
                    boolean isEnded = false;
                    int readChars =dataInputStream.available();

                    while(!isEnded && readChars>0){
                        String input = reader.readLine();
                        if(input.equals("\n" )|| input.equals("")){
                            isEnded = true;
                        }

                        else
                            inputString += input;
                        readChars--;
                    }

                    if(inputString!="")
                    *//*
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


                        inputString+=a;
                    }
                    if(dataInputStream.available()>0)
                    System.out.println("still aviable in data: " +dataInputStream.available());
                    if(socket.getInputStream().available()>0)
                    System.out.println("still aviable in socket: " +socket.getInputStream().available());
                    if (inputString.length()>0)
                    System.out.println("message length: "+inputString.length());
                     *//*
                    if(!inputString.equals("")){

                            String[] strings = inputString.split("\n");
                            for (String string :strings
                                 ) {
                                System.out.println("RECEIVED: " + inputString);

                                JsonObject jsonObject =  new Gson().fromJson(string, JsonObject.class);
                                process(jsonObject);


                            }

                        isEnded=false;

                    }
                }


            }catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    };*/



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

    public ArrayList<Client> getClientList(){
        return this.clientList;
    }
    public void setClientList(ArrayList<Client> clientList){
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



    public CPlayer getPlayer() {
      return player;
    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", figure=" + figure +
                ", id=" + id +
                '}';
    }
}
