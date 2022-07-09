package client_package.client_gamelogic;

import client_package.ClientObject;

public class OtherClient implements ClientObject {
    public final int clientID;
    public final int figure;
    public final String name;
    private final Game game;

    private boolean isReady = false;

    private CPlayer player;


    public OtherClient(Game game, int clientID, int figure, String name) {
        this.clientID = clientID;
        this.figure = figure;
        this.name = name;
        this.game = game;
    }

    public int getClientID() {
        return clientID;
    }

    public int getFigure() {
        return figure;
    }

    public String getName() {
        return name;
    }

    public CPlayer getPlayer() {
        return player;
    }

    public void setPlayer(CPlayer player) {
        this.player = player;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public int getId() {
        return clientID;
    }

    @Override
    public void setIsReady(boolean ready) {
        this.isReady = ready;
    }

    public boolean isReady() {
        return isReady;
    }
}
