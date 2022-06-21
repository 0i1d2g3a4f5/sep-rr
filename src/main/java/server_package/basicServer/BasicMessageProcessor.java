package server_package.basicServer;

import com.google.gson.JsonObject;
import newmessages.*;
import server_package.Client;
import server_package.MessageProcessor;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicMessageProcessor extends MessageProcessor {
    BasicClient client;

    public BasicMessageProcessor(Client client){
        super(client);

    }
    @Override
    public void process(JsonObject jsonObject){
        MessageType messageType = MessageType.valueOf(String.valueOf(jsonObject.get("type")));

        Message message = new MessageFactory().createMessage(messageType,jsonObject);
        message.activateMessage(client.getServer());


    }
}
