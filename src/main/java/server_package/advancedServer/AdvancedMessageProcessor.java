package server_package.advancedServer;


import com.google.gson.JsonObject;
import newmessages.ClientNotFoundException;
import newmessages.Message;
import newmessages.MessageFactory;
import newmessages.MessageType;
import server_package.MessageProcessor;

import java.io.IOException;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class AdvancedMessageProcessor extends MessageProcessor {
    AdvancedClient client;

    public AdvancedMessageProcessor(AdvancedClient client){
        super(client);
    }
    @Override
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        MessageType messageType = MessageType.valueOf(String.valueOf(jsonObject.get("type")));
        Message message = new MessageFactory().createMessage(messageType,jsonObject);
        message.activateMessage(client);


    }
}
