package newmessages;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageConnection extends Message{

    public MessageConnection(String name, int figure) {
        super();
        type = "Alive";
        System.out.println("Created SetName Message: " + this);
    }

    public MessageConnection(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created SetName Message: " + this + " from JSON: " + jsonObject);
    }
}

