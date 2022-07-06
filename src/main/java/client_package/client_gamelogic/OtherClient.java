package client_package.client_gamelogic;

import client_package.ClientObject;

public class OtherClient implements ClientObject {
    public final int clientID;
    public final int figure;
    public final String name;

    private boolean isReady = false;

    private Player player;


    public OtherClient(int clientID, int figure, String name) {
        this.clientID = clientID;
        this.figure = figure;
        this.name = name;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
