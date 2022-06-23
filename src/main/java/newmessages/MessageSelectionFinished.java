package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Isabel Muhm
 */

public class MessageSelectionFinished extends Message{

    public int clientID;

    public MessageSelectionFinished(int clientID){
        this.clientID = clientID;
        type = "SelectionFinished";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created SelectionFinished Message: " + this);
    }

    public MessageSelectionFinished(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clienID").getAsInt();
        System.out.println("Created SelectionFinished Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException, InterruptedException {
        if(client.getServer().getGame().programmingPlayers().size()==0)client.getServer().getGame().endProgrammingPhase();
        client.getPlayer().isProgramming = false;

    }
}
