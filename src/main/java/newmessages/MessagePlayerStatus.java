package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        System.out.println("Created Status Message: " + this);
    }

    public MessagePlayerStatus(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientId").getAsInt();
        ready = content.get("ready").getAsBoolean();
        System.out.println("Created Status Message: " + this + " from JSON: " + jsonObject);
    }
}
