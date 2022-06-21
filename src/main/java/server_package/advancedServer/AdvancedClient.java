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
public class AdvancedClient extends Client {
    private AdvancedServer server;
    private Player player;
    private int id;
    private String name;
    private Socket socket;
    private boolean isListening;





    public int getId(){
        return id;

    }

    void sendSingle(AdvancedClient client, Message message){
        try {
            OutputStream outputStream = client.socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(message.toJSON().toString());
            dataOutputStream.flush();
            System.out.println("SENT TO " + client.id + " :: " + message.toString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSelf(Message message) {
        sendSingle(this, message);
    }

    void sendAll(Message message) {
        for(AdvancedClient client : server.getClientList()){
            if(client.isListening)
                sendSingle(client, message);
        }
    }

    void sendList(List<AdvancedClient> clients, Message message) {
        for (AdvancedClient client : clients) {
            sendSingle(client, message);
        }
    }
}
