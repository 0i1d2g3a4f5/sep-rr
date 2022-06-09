package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageCheckPoint extends Message{

    public int clientID;
    public int number;

    public MessageCheckPoint(int clientID, int number){
        super(clientID,number);
        this.clientID = clientID;
        this.number = number;
        type = "CheckPointReached";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("number", new JsonPrimitive(number));
        content = jsonObject;
        System.out.println("Created CheckPoint Message: " + this);
    }

    public MessageCheckPoint(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        number = content.get("number").getAsInt();
        System.out.println("Created CheckPoint Message: " + this + " from JSON: " + jsonObject);
    }
}
