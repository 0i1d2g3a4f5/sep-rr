package server_package;

import com.google.gson.JsonObject;
import newmessages.ClientNotFoundException;
import newmessages.Message;
import newmessages.MessageFactory;
import newmessages.MessageType;
import server_package.advancedServer.AdvancedClient;

import java.io.IOException;

public abstract class MessageProcessor {

    Client client;

    public MessageProcessor(Client client){
        this.client=client;
    }
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        MessageType messageType = MessageType.valueOf(String.valueOf(jsonObject.get("type")));

        Message message = new MessageFactory().createMessage(messageType, jsonObject);
        message.activateMessage(client);
    }
}
