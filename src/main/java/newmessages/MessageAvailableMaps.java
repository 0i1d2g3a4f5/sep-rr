package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;

/**
 * @author Isabel Muhm
 */

public class MessageAvailableMaps extends Message{

    public ArrayList<String> availableMaps;

    public MessageAvailableMaps(ArrayList<String> availableMaps){
        super(availableMaps);
        this.availableMaps = availableMaps;
        type = "SelectMap";
        JsonObject jsonObject = new JsonObject();
        //jsonObject.add("availableMaps", new JsonPrimitive(availableMaps));
    }
}
