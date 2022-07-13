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


public class MessageCurrentPlayer extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageCurrentPlayer(int clientID) {
        this.clientID = clientID;
        type = "CurrentPlayer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created CPlayer Message: " + this);
        Server.serverLogger.info("Created CurrentPlayer Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageCurrentPlayer(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //System.out.println("Created CPlayer Message: " + this + " from JSON: " + jsonObject);
        //Server.serverLogger.info("Created CurrentPlayer Message: " + this + " from JSON: " + jsonObject);
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

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(client.getSocket().isConnected()){
            System.out.println("Current player is " + client.getName() + "," +client.getId());
            Server.serverLogger.info("Current player is " + client.getName() + "," + client.getId());
        }
    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
