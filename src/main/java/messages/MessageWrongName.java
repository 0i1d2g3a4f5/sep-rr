package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWrongName extends Message{

    public String name;

    /**
     * @param string
     */
    public MessageWrongName(String string) {
        type = "WrongName";
        this.name=string;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        content = jsonObject;
        //Server.serverLogger.info("Created Wrong Name Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageWrongName(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        //Server.serverLogger.info("Created Wrong Name Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
