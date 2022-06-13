package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Sarp Cagin Erdogan
 */

public class MessageWrongName extends Message{
    public String name;
    public MessageWrongName(String string) {
        super();
        type = "WrongName";
        this.name=string;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        content = jsonObject;
    }

    public MessageWrongName(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
    }
}
