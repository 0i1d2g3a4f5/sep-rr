package newmessages;

import com.google.gson.JsonObject;

/**
 * @author Isabel Muhm
 */

public class MessageTimerEnded extends Message{

    public MessageTimerEnded(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created StartTime Message: " + this);
    }

    public MessageTimerEnded(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created StartTime Message: " + this + " from JSON: " + jsonObject);
    }
}
