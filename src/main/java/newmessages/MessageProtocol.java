package newmessages;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * @author Sarp Cagin Erdogan
 */
public class MessageProtocol extends Message{
    public String protocol;

    public MessageProtocol(String string){
        super("");
        protocol=string;
        type="HelloClient";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("protocol", new JsonPrimitive(string));
        content=jsonObject;
        System.out.println("Created Protocol Message: " + this);
    }
    public MessageProtocol(JsonObject jsonObject){
        super(jsonObject);
        protocol=content.get("protocol").getAsString();
        System.out.println("Created Protocol Message: " + this + " from JSON: " + jsonObject);
    }

}
