package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageEnergy extends Message{

    public int clientID;
    public int count;
    public String source;

    /**
     * converts message to json
     *
     * @param clientID
     * @param count
     * @param source
     * @author Isabel Muhm
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
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageEnergy(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        count = content.get("count").getAsInt();
        source = content.get("source").getAsString();
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
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }

}
