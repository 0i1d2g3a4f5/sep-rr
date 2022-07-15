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


public class MessageCheckPointReached extends Message{

    public int clientID;
    public int number;

    /**
     * @param clientID
     * @param number
     */
    public MessageCheckPointReached(int clientID, int number){
        this.clientID = clientID;
        this.number = number;
        type = "CheckPointReached";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("number", new JsonPrimitive(number));
        content = jsonObject;
        //Server.serverLogger.info("Created Check Point Reached Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageCheckPointReached(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        number = content.get("number").getAsInt();
        //Server.serverLogger.info("Created Check Point Reached Message: " + this + " from JSON: " + jsonObject);
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
