package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageNameSet extends Message{

    public int clientID;
    public String name;
    public int figure;

    public MessageNameSet(int clientID, String name, int figure) {
        super(clientID, name, figure);
        this.clientID = clientID;
        this.name = name;
        this.figure = figure;
        type = "PlayerAdded";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
        System.out.println("Created NameSet Message: " + this);
    }

    public MessageNameSet(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        System.out.println("Created NameSet Message: " + this + " from JSON: " + jsonObject);
    }

}
