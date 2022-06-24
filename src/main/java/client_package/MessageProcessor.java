package client_package;

import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class MessageProcessor {
    protected Client client;
    private boolean isBasic;
    public MessageProcessor(Client client, boolean isBasic){
        setClient(client);
        setBasic(isBasic);

    }

    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
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
    public boolean getBasic(){
        return isBasic;
    }

    public void setBasic(boolean basic) {
        isBasic = basic;
    }
}

