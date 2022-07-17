package newmessages;

import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageNameUnavailable extends Message{

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
        //Server.serverLogger.info("Created Name Unavailable Message: " + this);
    }

    /**
     * @param jsonObject
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
