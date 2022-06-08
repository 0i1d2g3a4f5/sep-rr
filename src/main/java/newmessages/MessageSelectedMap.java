package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageSelectedMap extends Message{

    public String map;

    MessageSelectedMap(String map) {
        super(map);
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
        System.out.println("Created MapSelected Message: " + this);
    }

    public MessageSelectedMap(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
        System.out.println("Created MapSelected Message: " + this + " from JSON: " + jsonObject);
    }
}
