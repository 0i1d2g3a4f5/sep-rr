package client_package.client_gamelogic;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getActiveRegister() {
        return activeRegister;
    }

    public void setActiveRegister(int activeRegister) {
        this.activeRegister = activeRegister;
    }

    private int activeRegister, phase;

    private final ArrayList<CPlayer> playerList = new ArrayList<>();

    private GameBoard map;

    private Client client;

    private static Game instance;

    private Game() {
    }

    public static Game getInstance() throws IOException {
        if(instance != null) return  instance;
        else {
            instance =  new Game();
            return instance;
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

    private Game(Client client, ArrayList<CPlayer> playerList, JsonObject mapJson) throws IOException {
        this.client = client;
        //TODO check if needed: this.playerList = playerList;
        map = new GameBoard(mapJson);
    }

}
