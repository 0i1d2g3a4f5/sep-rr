package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Qinyi
 * update the status the client, leaved or rejioning
 */
public class MessageConnectionUpdate extends Message{
    public int clientID;

    public boolean isConnected;

    public String action;


    /**
     *
     * @param clientID
     * @param isConnected
     * @param action
     */
    public MessageConnectionUpdate(int clientID, boolean isConnected, String action){
        this.clientID = clientID;
        this.isConnected = isConnected;
        this.action = action;
        type = "ConnectionUpdate";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID",new JsonPrimitive(clientID));
        jsonObject.add("isConnected",new JsonPrimitive(isConnected));
        jsonObject.add("action",new JsonPrimitive(action));
        content = jsonObject;

    }

    public MessageConnectionUpdate(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        isConnected = content.get("isConnected").getAsBoolean();
        action = content.get("content").getAsString();
        //Server.serverLogger.info("Created ConnectionUpdate Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     *
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient client, boolean isBasic) throws IOException, ClientNotFoundException{


    }

    /**
     *
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     *
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
