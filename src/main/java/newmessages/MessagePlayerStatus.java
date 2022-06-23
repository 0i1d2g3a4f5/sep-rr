package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessagePlayerStatus extends Message {

    public int clientID;
    public boolean ready;

    public MessagePlayerStatus(int clientID, boolean ready){
        super(clientID,ready);
        this.clientID = clientID;
        this.ready = ready;
        type = "PlayerStatus";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("AA", new JsonPrimitive(clientID));
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        //System.out.println("Created Status Message: " + this);
    }

    public MessagePlayerStatus(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("AA").getAsInt();
        ready = content.get("ready").getAsBoolean();
        //System.out.println("Created Status Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
