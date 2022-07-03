package server_package;

import com.google.gson.JsonObject;
import newmessages.*;
import server_package.newmessages.*;

import java.io.IOException;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class MessageProcessor {

    private Client client;
    private boolean isBasic;

    public MessageProcessor(Client client, boolean isBasic){
        this.client=client;
        this.isBasic=isBasic;
    }
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInBackend(client, isBasic);
    }
}
