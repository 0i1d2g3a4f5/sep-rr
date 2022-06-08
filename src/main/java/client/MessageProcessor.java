package client;

import com.google.gson.JsonObject;
import newmessages.Message;
import newmessages.MessageProtocol;
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
        System.out.println("To Process: " + message.toJSON());
        switch (message.type){
            case "HelloClient" -> {
                MessageProtocol messageProtocol = new MessageProtocol(jsonObject);
                if(messageProtocol.protocol.equals("Version 0.1")){
                    System.out.println("Correct communication protocol verified.");
                }
                else{
                    System.out.println("Error: False communication protocol.");
                }

            }
            default -> {

            }
        }

    }
}
