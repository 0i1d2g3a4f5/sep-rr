package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageEnergy extends Message{

    public int clientID;
    public int count;
    public String source;

    /**
     * @param clientID
     * @param count
     * @param source
     */
    public MessageEnergy(int clientID, int count, String source){

        this.clientID = clientID;
        this.count = count;
        this .source = source;
        type = "Energy";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("count", new JsonPrimitive(count));
        jsonObject.add("source", new JsonPrimitive(source));
        content = jsonObject;
        //Server.serverLogger.info("Created Energy Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageEnergy(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        count = content.get("count").getAsInt();
        source = content.get("source").getAsString();
        // Server.serverLogger.info("Created Energy Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param server
     * @throws IOException
     * @throws ClientNotFoundException
     */

}
