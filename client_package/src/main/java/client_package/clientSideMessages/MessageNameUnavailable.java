package client_package.clientSideMessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageNameUnavailable extends Message {

    public String name;

    /**
     *
     * @param string
     */
    public MessageNameUnavailable(String string){
        type="NameUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("UnavailableName", new JsonPrimitive(string));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
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
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
