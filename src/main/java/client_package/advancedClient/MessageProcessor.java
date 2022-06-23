package client_package.advancedClient;

import client_package.Client;
import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;

public class MessageProcessor extends client_package.MessageProcessor {
    private Client client;
    public MessageProcessor(Client client) {
        super(client);
    }
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        process(jsonObject, false);
    }

/* GETTER SETTER
*
*
*
*
*
*
 */
    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }
}
