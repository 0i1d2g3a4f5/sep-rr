package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHashedCode extends Message{
    public String hashedCode;

    /**
     * converts message to json
     *
     * @param string
     * @author Isabel Muhm
     */
    public MessageHashedCode(String string){
        hashedCode=string;
        type="HashedCode";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("HashedCode", new JsonPrimitive(hashedCode));
        content=jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageHashedCode(JsonObject jsonObject){
        super(jsonObject);
        hashedCode=content.get("HashedCode").getAsString();
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
