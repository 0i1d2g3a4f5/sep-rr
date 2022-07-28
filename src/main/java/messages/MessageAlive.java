package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageAlive extends Message{

    /**
     * converts message to JSON
     *
     * @author Isabel Muhm
     */
    public MessageAlive() {
        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    /**
     * converts json to Message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
    }

    /**
     * documents, that server is still connected to client
     *
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        Server.serverLogger.info("SClient " + sClient.getId() + " is still connected to server");
    }

    /**
     * returns message to server to show client is alive
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        client.sendSelf(new MessageAlive());
    }
    /**
     * returns message to server to show AI is alive
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.sendSelf(new MessageAlive());

    }
}
