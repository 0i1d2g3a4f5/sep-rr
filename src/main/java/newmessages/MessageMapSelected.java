package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageMapSelected extends Message{

    public String map;

    MessageMapSelected(String map) {
        super(map);
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
        System.out.println("Created MapSelected Message: " + this);
    }

    public MessageMapSelected(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
        System.out.println("Created MapSelected Message: " + this + " from JSON: " + jsonObject);
    }
}
