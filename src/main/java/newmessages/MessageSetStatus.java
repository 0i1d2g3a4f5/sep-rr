package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageSetStatus extends Message{

    public boolean ready;

    public MessageSetStatus(boolean ready) {
        super(ready);
        this.ready = ready;
        type = "SetStatus";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        System.out.println("Created SetReady Message: " + this);
    }

    public MessageSetStatus(JsonObject jsonObject){
        ready = content.get("ready").getAsBoolean();
        System.out.println("Created SetReady Message: " + this + " from JSON: " + jsonObject);
    }

}
