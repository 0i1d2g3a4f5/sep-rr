package client_package;

import client_application.ClientApplication;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.game_elements.ElementName;
import client_package.client_gamelogic.game_elements.GameElement;
import com.google.gson.JsonObject;
import gamelogic.Color;
import gamelogic.Position;
import javafx.application.Platform;
import messages.Message;
import messages.MessageConnectionUpdate;
import messages.MessageSetStartingPoint;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class Client{
    protected boolean startingPointsReady, thereIsStartingToRemove;

    public boolean isStartingPointsReady() {
        return startingPointsReady;
    }
    public void chooseStartingPoint(){
        while (!isStartingPointsReady() || isThereIsStartingToRemove()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int random = (int) Math.random()*getStartingPoints().size();
        Position position = getStartingPoints().get(random);
        sendSelf(new MessageSetStartingPoint(position.getX(), position.getY()));
    }
    public void removeStartingPoint(Position position){
        getStartingPoints().remove(position);
        setThereIsStartingToRemove(false);

    }
    public void setStartingPointsReady(boolean startingPointsReady) {
        this.startingPointsReady = startingPointsReady;
    }

    public boolean isThereIsStartingToRemove() {
        return thereIsStartingToRemove;
    }

    public void setThereIsStartingToRemove(boolean thereIsStartingToRemove) {
        this.thereIsStartingToRemove = thereIsStartingToRemove;
    }

    public static Logger clientLogger = Logger.getLogger("Client");
    protected ArrayList<String> receivedMessages = new ArrayList<>();
    protected ArrayList<Position> startingPoints = new ArrayList<>();

    public ArrayList<Position> getStartingPoints() {
        return startingPoints;
    }
    public void initializeStartingPoints(){
        while (getGame().getMap()==null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int x = 0; x<getGame().getMap().getDimensionX(); x++){
            for(int y = 0; y<getGame().getMap().getDimensionY(); y++){
                for(GameElement gameElement : getGame().getMap().getGameField(y, x).getElements()){
                    if(gameElement.getType().equals(ElementName.STARTPOINT)){
                        getStartingPoints().add(new Position(y, x));
                    }
                }
            }
        }
        startingPointsReady=true;
    }
    public void setStartingPoints(ArrayList<Position> startingPoints) {
        this.startingPoints = startingPoints;
    }

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

        startingPointsReady=false;
        thereIsStartingToRemove=false;
    }
    public Client(Game game, int id, int figure, String name){
        setGame(game);
        setId(id);
        setName(name);
        setFigure(figure);

        startingPointsReady=false;
        thereIsStartingToRemove=false;
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
