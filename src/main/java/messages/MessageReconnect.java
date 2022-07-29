package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageReconnect extends Message{
    public String name;
    public String hash;

    /**
     * from message to json
     *
     * @param name
     * @param hash
     */
    public MessageReconnect(String name, String hash){
        this.name=name;
        this.hash=hash;
        type="Reconnect";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Hash", new JsonPrimitive(hash));
        content = jsonObject;
    }

    /**
     * from json to message
     *
     * @param jsonObject
     */
    public MessageReconnect(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        hash=content.get("Hash").getAsString();
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {}

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {}

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
