package client_package;

import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public abstract class MessageProcessor {
    protected Client client;
    public MessageProcessor(Client client){
        setClient(client);
    }

    public void process(JsonObject jsonObject, boolean isBasic) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("MessageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInFrontend(client, isBasic);
    }


    /* GETTER SETTER
    *
    *
    *
    *
    *
     */

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

