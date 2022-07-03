package client_package.clientSideMessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageReconnect extends Message {
    public String name;
    public String hash;

    /**
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
     * @param jsonObject
     */
    public MessageReconnect(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        hash=content.get("Hash").getAsString();
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

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}