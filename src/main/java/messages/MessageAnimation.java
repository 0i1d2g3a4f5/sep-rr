package messages;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageAnimation extends Message{

    public String animationType;

    /**
     * converts Message to json
     *
     * @param animationType
     * @author Isabel Muhm
     */
    public MessageAnimation(String animationType){

        this.animationType = animationType;
        type = "Animation";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(animationType));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageAnimation(JsonObject jsonObject){
        super(jsonObject);
        animationType = content.get("type").getAsString();
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
