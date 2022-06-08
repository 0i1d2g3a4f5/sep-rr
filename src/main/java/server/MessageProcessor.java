package server;

import com.google.gson.JsonObject;
import newmessages.Message;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageProcessor {
    Client client;
    MessageProcessor(Client client){
        this.client=client;
    }
    void process(JsonObject jsonObject){
        Message message = new Message(jsonObject);
        switch (message.type){
            default -> {

            }
        }

    }
}
