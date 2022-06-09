package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;

/**
 * @author Isabel Muhm
 */

public class MessageEndTime extends Message{

    //public ArrayList<int> players;

    public MessageEndTime(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created EndTime Message: " + this);
    }

    public MessageEndTime(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created EndTime Message: " + this + " from JSON: " + jsonObject);
    }
}
