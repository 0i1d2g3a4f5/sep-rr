package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageSelectionFinished extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageSelectionFinished(int clientID){
        this.clientID = clientID;
        type = "SelectionFinished";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //Server.serverLogger.info("Created Selection Finished Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSelectionFinished(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //Server.serverLogger.info("Created Selection Finished Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @author Ringer
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        /*
        try {
            if (sClient.getServer().getGame().programmingPlayers().size() == 0)
                sClient.getServer().getGame().endProgrammingPhase();
            sClient.getPlayer().isProgramming = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         */

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        Server.serverLogger.info("Server: Player " + clientID + " has finished programming. The others have 30 seconds left");

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
