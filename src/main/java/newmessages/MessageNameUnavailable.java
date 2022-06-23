package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageNameUnavailable extends Message{
    public String name;
    public MessageNameUnavailable(String string){
        super(string);
        type="NameUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("UnavailableName", new JsonPrimitive(string));
        content = jsonObject;
    }
    public MessageNameUnavailable(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("UnavailableName").getAsString();
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
