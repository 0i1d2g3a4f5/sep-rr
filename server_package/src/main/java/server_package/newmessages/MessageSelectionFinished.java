package server_package.newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

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
        System.out.println("Created SelectionFinished Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSelectionFinished(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clienID").getAsInt();
        System.out.println("Created SelectionFinished Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @author Ringer
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        try {
            if (client.getServer().getGame().programmingPlayers().size() == 0)
                client.getServer().getGame().endProgrammingPhase();
            client.getPlayer().isProgramming = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
}
