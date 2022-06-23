package server_package.advancedServer;

import gamelogic.Player;
import newmessages.Message;
import server_package.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
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


}
