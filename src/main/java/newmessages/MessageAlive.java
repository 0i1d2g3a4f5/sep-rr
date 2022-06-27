package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageAlive extends Message{

    public MessageAlive() {

        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        //System.out.println("Created ALive Message: " + this);
    }

    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
        //System.out.println("Created Alive Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
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
