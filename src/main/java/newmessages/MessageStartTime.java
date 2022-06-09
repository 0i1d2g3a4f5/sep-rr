package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageStartTime extends Message{

    public MessageStartTime(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created StartTime Message: " + this);
    }

    public MessageStartTime(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created StartTime Message: " + this + " from JSON: " + jsonObject);
    }
}
