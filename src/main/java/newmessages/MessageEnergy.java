package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageEnergy extends Message{

    public int clientID;
    public int count;
    public String source;

    public MessageEnergy(int clientID, int count, String source){
        super(clientID, count, source);
        this.clientID =clientID;
        this.count = count;
        this .source = source;
        type = "Energy";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("count", new JsonPrimitive(count));
        jsonObject.add("source", new JsonPrimitive(source));
        content = jsonObject;
        System.out.println("Created Energy Message: " + this);
    }

    public MessageEnergy(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        count = content.get("count").getAsInt();
        source = content.get("source").getAsString();
        System.out.println("Created Energy Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }

    /**
     * @param server
     * @throws IOException
     * @throws ClientNotFoundException
     */

}
