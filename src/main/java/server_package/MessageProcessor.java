package server_package;

import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class MessageProcessor {

    private SClient sClient;
    private boolean isBasic;

    public MessageProcessor(SClient sClient, boolean isBasic){
        this.sClient = sClient;
        this.isBasic=isBasic;
    }
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        MessageType messageType = new MessageTypeFactory().fromString(jsonObject.get("messageType").getAsString());
        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessageInBackend(sClient, isBasic);
    }
}
