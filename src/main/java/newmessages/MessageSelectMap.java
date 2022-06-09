package newmessages;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * @author Isabel Muhm
 */

public class MessageSelectMap extends Message{

    public ArrayList<String> availableMaps;

    public MessageSelectMap(ArrayList<String> availableMaps){
        super(availableMaps);
        this.availableMaps = availableMaps;
        type = "SelectMap";
        JsonObject jsonObject = new JsonObject();
        //jsonObject.add("availableMaps", new JsonPrimitive(availableMaps));
    }
}
