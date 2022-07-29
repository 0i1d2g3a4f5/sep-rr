package client_package.client_gamelogic;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the Game-State clientside.
 * its contains  methods that mirror the gamelogic
 * and serves as connection between
 * the different parts of the Game.Game class
 * it serves only for visual Representation.
 */

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

    /**
     * returns the only instance of Game
     * @return
     * @throws IOException
     * @author Mark Ringer
     */
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
        map = new GameBoard(mapJson);
    }

}
