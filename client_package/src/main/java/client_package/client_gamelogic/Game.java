package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> playerList;
    private GameBoard map;
    private Client client;

    public Game(Client client, ArrayList<Player> playerList, JsonObject mapJson) throws IOException {
        this.client = client;
        this.playerList = playerList;
        map = new GameBoard(mapJson);
    }


    public Game() {

    }
}