package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import javax.swing.*;

/**
 * @author Isabel Muhm
 */

public class MessageStatus extends Message {

    public int clientID;
    public boolean ready;

    public MessageStatus(int clientID, boolean ready){
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

    public MessageStatus(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientId").getAsInt();
        ready = content.get("ready").getAsBoolean();
        System.out.println("Created Status Message: " + this + " from JSON: " + jsonObject);
    }
}
