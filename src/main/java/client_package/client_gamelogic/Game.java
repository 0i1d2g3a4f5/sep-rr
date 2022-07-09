package client_package.client_gamelogic;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private int activeRegister;
    private final ArrayList<CPlayer> playerList = new ArrayList<>();
    private GameBoard map;
    private Client client;
    private static Game instance;

    public Game() {

    }


    public static Game getInstance() throws IOException {
        if(instance != null) return  instance;
        else return new Game();
    }

    public int getActiveRegister() {
        return activeRegister;
    }

    public void join(ClientObject client){
        if(!(client.getId()==this.client.getId())){
            OtherClient otherClient = (OtherClient) client;
            CPlayer player = new CPlayer(client,this);

            otherClient.setPlayer(player);
            playerList.add(player);
        } else {
            Client thisClient = (Client) client;
            ThisCPlayer player = new ThisCPlayer(thisClient,thisClient.getGame());

            thisClient.setPlayer(player);
            playerList.add(player);
        }
    }

    public ArrayList<CPlayer> getPlayerList() {
        return playerList;
    }



    public GameBoard getMap() {
        return map;
    }

    public void setMap(GameBoard map) {
        this.map = map;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Game(Client client, ArrayList<CPlayer> playerList, JsonObject mapJson) throws IOException {
        this.client = client;
        //TODO check if needed: this.playerList = playerList;
        map = new GameBoard(mapJson);
    }



}
