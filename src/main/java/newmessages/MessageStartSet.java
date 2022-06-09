package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageStartSet extends Message{

    public int x;
    public int y;
    public int clientID;

    public MessageStartSet(int x, int y, int clientID){
        super(x,y,clientID);
        this.x = x;
        this.y = y;
        this.clientID = clientID;
        type = "StartingPointTaken";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created StartSet Message: " + this);
    }

    public MessageStartSet(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x"). getAsInt();
        y = content.get("y"). getAsInt();
        clientID = content.get("clientID"). getAsInt();
    }
}
