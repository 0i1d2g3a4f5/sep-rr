package client;

import com.google.gson.JsonObject;
import newmessages.*;

/**
 * @author Sarp Cagin Erdogan
 * @author Isabel Muhm
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
                //optimalerweise werden in diesem case auch die beresits verbundenen Clients und deren Status übergeben
                MessageProtocol messageProtocol = new MessageProtocol(jsonObject);
                if(messageProtocol.protocol.equals("Version 0.1")){
                    System.out.println("Correct communication protocol verified.");
                }
                else{
                    System.out.println("Error: False communication protocol.");
                }

            }
            case "Welcome" -> {
                MessageWelcome messageWelcome = new MessageWelcome(jsonObject);
                System.out.println("Handed over Client ID " + messageWelcome.clientID);
            }
            case "PLayerAdded" -> {
                MessageNameSet messageNameSet = new MessageNameSet(jsonObject);
                System.out.println("Name " + messageNameSet.name + " and figure number " + messageNameSet.figure + " set");
            }
            case "Alive" -> {
                MessageWelcome messageWelcome = new MessageWelcome(jsonObject);
                System.out.println("Server ist still connected to client");
            }
            case "PlayerStatus" -> {
                MessageStatus messageStatus = new MessageStatus(jsonObject);
                if(messageStatus.ready) {
                    System.out.println("Player with clientID " + messageStatus.clientID + " is ready");
                }
                else {
                    System.out.println("Player with clientID " + messageStatus.clientID + " is not ready");
                }
            }
            case "MapSelected" -> {
                MessageSelectedMap messageSelectedMap = new MessageSelectedMap(jsonObject);
                System.out.println("New Selected map: " + messageSelectedMap.map + "was set");
            }
            default -> {

            }
        }

    }
}
