package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;
import server_package.Server;


/**
 * @author Isabel Muhm
 */

public class MessageActivePhase extends Message{

    public int phase;

    public MessageActivePhase(int phase){
        super(phase);
        this.phase = phase;
        type = "ActivePhase";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("phase", new JsonPrimitive(phase));
        content = jsonObject;
        System.out.println("Created Phase Message: " + this);
    }

    public MessageActivePhase(JsonObject jsonObject) {
        super(jsonObject);
        phase = content.get("phase").getAsInt();
        System.out.println("Created Phase Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     */
    @Override
    public void activateMessage(Client client) {

    }
}