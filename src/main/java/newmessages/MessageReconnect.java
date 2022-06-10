package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class MessageReconnect extends Message{
    public String name;
    public String hash;
    public MessageReconnect(String name, String hash){
        this.name=name;
        this.hash=hash;
        type="Reconnect";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Hash", new JsonPrimitive(hash));
        content = jsonObject;
    }
    public MessageReconnect(JsonObject jsonObject){
        super(jsonObject);
    }
}
