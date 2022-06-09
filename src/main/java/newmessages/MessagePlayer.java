package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessagePlayer extends Message{

    public int clientID;

    public MessagePlayer(int clientID) {
        super(clientID);
        this.clientID = clientID;
        type = "CurrenPlayer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Player Message: " + this);
    }

    public MessagePlayer(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Player Message: " + this + " from JSON: " + jsonObject);
    }
}
