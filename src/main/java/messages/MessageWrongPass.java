package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWrongPass extends Message{
    public MessageWrongPass() {
        type = "WrongPass";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    /**
     * converts message to json
     *
     * @param jsonObject
     * @author Isbael Muhm
     */
    public MessageWrongPass(JsonObject jsonObject){
        super(jsonObject);
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
