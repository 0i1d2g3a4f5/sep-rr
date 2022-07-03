package server_package.advancedServer;

import server_package.gamelogic.Player;
import server_package.Client;

import java.net.Socket;

/**
 * @author Sarp Cagin Erdogan
 */
public class AdvancedClient extends Client{
    private AdvancedServer server;
    private Player player;
    private int id;
    private String name;
    private Socket socket;
    private boolean isListening;
    public AdvancedClient(){
        super();
    }





    public int getId(){
        return id;

    }

    @Override
    public void listen() {

    }

    @Override
    public void sendProtocolCheck() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void removeClientFromList() {

    }

    @Override
    public void shutDownClient() {

    }

    @Override
    public void checkValues(String name, int figure) {

    }

    @Override
    public void sendPreviousInfo() {

    }


}
