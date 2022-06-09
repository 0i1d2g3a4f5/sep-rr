package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessagePhase extends Message{

    public int phase;

    public MessagePhase(int phase){
        super(phase);
        this.phase = phase;
        type = "ActivePhase";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("phase", new JsonPrimitive(phase));
        content = jsonObject;
        System.out.println("Created Phase Message: " + this);
    }

    public MessagePhase(JsonObject jsonObject) {
        super(jsonObject);
        phase = content.get("phase").getAsInt();
        System.out.println("Created Phase Message: " + this + " from JSON: " + jsonObject);
    }
}
