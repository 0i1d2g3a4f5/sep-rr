package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageRoboReboot extends Message{

    public int clientID;

    public MessageRoboReboot(int clientID){
        super(clientID);
        this.clientID = clientID;
        type = "Reboot";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created RoboReboot Message: " + this);
    }

    public MessageRoboReboot(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created RoboReboot Message: " + this + " from JSON: " + jsonObject);
    }
}
