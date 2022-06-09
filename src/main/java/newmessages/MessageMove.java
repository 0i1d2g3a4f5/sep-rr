package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageMove extends Message{

    public int clientID;
    public int x;
    public int y;

    public MessageMove(int clientID, int x, int y){
        super(clientID, x, y);
        this.clientID = clientID;
        this.x = x;
        this.y = y;
        type = "Movement";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        System.out.println("Created Move Message: " + this);
    }

    public MessageMove(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        x= content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        System.out.println("Created Move Message: " + this + " from JSON: " + jsonObject);
    }
}
