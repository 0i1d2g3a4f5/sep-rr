package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageReconnect extends Message{
    public String name;
    public String hash;
    public MessageReconnect(String name, String hash){
        super(name, hash);
        this.name=name;
        this.hash=hash;
        type="Reconnect";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Hash", new JsonPrimitive(hash));
        content = jsonObject;
    }
    public MessageReconnect(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        hash=content.get("Hash").getAsString();
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }
}