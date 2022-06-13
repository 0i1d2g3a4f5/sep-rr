package newmessages;

import com.google.gson.JsonObject;
/**
 * @author Sarp Cagin Erdogan
 */

public class MessageWrongPass extends Message{
    public MessageWrongPass() {
        super();
        type = "WrongPass";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    public MessageWrongPass(JsonObject jsonObject){
        super(jsonObject);
    }
}
