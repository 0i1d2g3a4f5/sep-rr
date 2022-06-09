package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageTurn extends Message{

    public int clientID;
    public String rotation;

    public MessageTurn(int clientID, String rotation){
        super(clientID, rotation);
        this.clientID = clientID;
        this.rotation = rotation;
        type = "PlayerTurning";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clienID", new JsonPrimitive(clientID));
        jsonObject.add("rotation", new JsonPrimitive(rotation));
        content = jsonObject;
        System.out.println("Created Turn Message: " + this);
    }

    public MessageTurn(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        rotation = content.get("rotation").getAsString();
        System.out.println("Created Turn Message: " + this + " from JSON: " + jsonObject);
    }
}
