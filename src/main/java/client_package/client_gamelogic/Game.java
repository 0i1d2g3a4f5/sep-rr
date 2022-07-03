package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.MapName;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private ArrayList<Player> playerList;
    private GameBoard map;
    private Client client;
    private static Game instance;

    public Game() {

    }

    public static Game getInstance() throws IOException {
        if(instance != null) return  instance;
        else return new Game();
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
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

    public Game(Client client, ArrayList<Player> playerList, JsonObject mapJson) throws IOException {
        this.client = client;
        this.playerList = playerList;
        map = new GameBoard(mapJson);
    }



}
