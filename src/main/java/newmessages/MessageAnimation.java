package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageAnimation extends Message{

    public String animationType;

    /**
     * @param animationType
     */
    public MessageAnimation(String animationType){

        this.animationType = animationType;
        type = "Animation";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(animationType));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageAnimation(JsonObject jsonObject){
        super(jsonObject);
        animationType = content.get("type").getAsString();
        //Server.serverLogger.info("Created Animation Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
