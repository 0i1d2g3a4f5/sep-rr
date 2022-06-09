package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageReceived extends Message{

    public String message;
    public int from;
    public boolean isPrivate;

    public MessageReceived(String message, int from, boolean isPrivate) {
        super(message, from, isPrivate);
        this.message = message;
        this.from = from;
        this.isPrivate = isPrivate;
        type = "ReceivedChat";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", new JsonPrimitive(message));
        jsonObject.add("from", new JsonPrimitive(from));
        jsonObject.add("isPrivate", new JsonPrimitive(isPrivate));
        content = jsonObject;
        System.out.println("Created Received Message: " + this);
    }

    public MessageReceived(JsonObject jsonObject){
        super(jsonObject);
        message = content.get("message").getAsString();
        from = content.get("from").getAsInt();
        isPrivate = content.get("isPrivate").getAsBoolean();
        System.out.println("Created Received Message: " + this + " from JSON: " + jsonObject);
    }
}
