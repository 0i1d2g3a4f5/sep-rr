package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageRebootDirection extends Message{

    public String direction;

    public MessageRebootDirection(String direction){
        super(direction);
        this.direction = direction;
        type = "RebootDirection";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("direction", new JsonPrimitive(direction));
        content = jsonObject;
        System.out.println("Created RebootDirection Message: " + this);
    }

    public MessageRebootDirection(JsonObject jsonObject){
        super(jsonObject);
        direction = content.get("direction").getAsString();
        System.out.println("Created RebootDirection Message: " + this + " from JSON: " + jsonObject);
    }
}
