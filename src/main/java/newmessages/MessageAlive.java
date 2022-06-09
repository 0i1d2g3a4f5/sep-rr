package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageAlive extends Message{

    public MessageAlive() {
        super();
        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created ALive Message: " + this);
    }

    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created Alive Message: " + this + " from JSON: " + jsonObject);
    }
}
