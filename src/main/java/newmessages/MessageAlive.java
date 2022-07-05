package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import server_package.SClient;

import java.io.IOException;

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
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        System.out.println("SClient " + sClient.getId() +" is still connected to server");
        //TODO server sends message every 5 seconds an ist not answering on sClient messages
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
