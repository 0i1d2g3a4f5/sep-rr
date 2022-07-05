package server_package.advancedServer;

import gamelogic.Player;
import server_package.SClient;

import java.net.Socket;

/**
 * @author Sarp Cagin Erdogan
 */
public class AdvancedSClient extends SClient {
    private AdvancedServer server;
    private Player player;
    private int id;
    private String name;
    private Socket socket;
    private boolean isListening;
    public AdvancedSClient(){
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
