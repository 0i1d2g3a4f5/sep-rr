package server_package;

import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public abstract class MessageProcessor {

    private Client client;

    public MessageProcessor(Client client){
        this.client=client;
    }
    public void process(JsonObject jsonObject, boolean isBasic) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("MessageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInBackend(client, isBasic);
    }
}
