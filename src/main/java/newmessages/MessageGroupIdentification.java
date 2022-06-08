package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */
public class MessageGroupIdentification extends Message {

    public String group;
    public boolean isAI;
    public String protocol;

    public MessageGroupIdentification(String group, boolean isAI, String protocol) {
        super(group, isAI, protocol);
        this.group = group;
        this.isAI = isAI;
        this.protocol = protocol;
        type = "HelloServer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("group", new JsonPrimitive(group));
        jsonObject.add("isAI", new JsonPrimitive(isAI));
        jsonObject.add("protocol", new JsonPrimitive(protocol));
        content = jsonObject;
        System.out.println("Created GroupIdentification Message: " + this);
    }

    public MessageGroupIdentification(JsonObject jsonObject) {
        super(jsonObject);
        group = content.get("group").getAsString();
        isAI = content.get("isAI").getAsBoolean();
        protocol = content.get("protocol").getAsString();
        System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
    }
}
