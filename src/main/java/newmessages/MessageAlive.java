package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageAlive extends Message{

    public MessageAlive() {
        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
    }

    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        System.out.println("Check the Status.");
        //TODO server sends message every 5 seconds an ist not answering on client messages
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
            if(client.getSocket().isConnected()){
                System.out.println("Client " + client.getId() +" is still connected to server");
            }
            else{
                System.out.println("Client " + client.getId() + "is disconnected." );
            }
        }
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            client.sendSelf(new MessageAlive());
        } else{

        }

    }
    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            client.sendSelf(new MessageAlive());
        } else{

        }

    }
}
