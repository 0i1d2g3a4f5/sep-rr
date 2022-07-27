package messages;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageCheckPointReached extends Message{

    public int clientID;
    public int number;

    /**
     * converts message to json
     *
     * @param clientID
     * @param number
     * @author Isabel Muhm
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
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageCheckPointReached(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        number = content.get("number").getAsInt();
        //Server.serverLogger.info("Created Check Point Reached Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }

    /**
     * informs AI that it reached a checkpoint and now has to reach the next one
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            sentientClient.getSentientBehaviour().nextCheckPoint();
        }

    }

}
