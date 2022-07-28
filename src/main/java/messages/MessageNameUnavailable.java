package messages;

import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageNameUnavailable extends Message{

    public String name;

    /**
     * converts message to json
     *
     * @param string
     * @author Isabel Muhm
     */
    public MessageNameUnavailable(String string){
        type="NameUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("UnavailableName", new JsonPrimitive(string));
        content = jsonObject;
        //Server.serverLogger.info("Created Name Unavailable Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageNameUnavailable(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("UnavailableName").getAsString();
        //Server.serverLogger.info("Created Name Unavailable Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
