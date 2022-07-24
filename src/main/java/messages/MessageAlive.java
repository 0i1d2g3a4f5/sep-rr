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

    public MessageAlive() {
        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        Server.serverLogger.info("SClient " + sClient.getId() + " is still connected to server");
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        client.sendSelf(new MessageAlive());

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.sendSelf(new MessageAlive());

    }
}
