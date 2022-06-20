package advancedServer;

import gamelogic.Player;
import newmessages.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public class Client {
    AdvancedServer server;
    int id;
    boolean isListening;
    String name;
    Socket socket;
    Player player;


    public int getId(){
        return id;

    }

    void sendSingle(Client client, Message message){
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
        for(Client client : server.clientList){
            if(client.isListening)
                sendSingle(client, message);
        }
    }

    void sendList(List<Client> clients, Message message) {
        for (Client client : clients) {
            sendSingle(client, message);
        }
    }
}
