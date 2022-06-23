package newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;


/**
 * @author Sarp Cagin Erdogan, Isabel Muhm
 */

public class MessageSelectMap extends Message{

    public JsonArray availableMaps;

    public MessageSelectMap(JsonArray jsonArray){
        super(jsonArray);
        type = "SelectMap";
        this.availableMaps = jsonArray;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("availableMaps", jsonArray);
        content=jsonObject;
    }
    public MessageSelectMap(JsonObject jsonObject){
        super(jsonObject);
        availableMaps=content.get("availableMaps").getAsJsonArray();
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }


}
