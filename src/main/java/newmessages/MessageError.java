package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageError extends Message{

    public String error;

    public MessageError(String error){
        super(error);
        this.error = error;
        type = "Error";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", new JsonPrimitive(error));
        content = jsonObject;
        //System.out.println("Created Error Message: " + this);
    }

    public MessageError(JsonObject jsonObject) {
        super(jsonObject);
        error = content.get("error").getAsString();
        //System.out.println("Created Error Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param advanced
     */
    @Override
    public void activateMessage(boolean advanced) {

    }
}
