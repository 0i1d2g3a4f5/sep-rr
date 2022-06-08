package server;

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
        switch (message.type){
            case "HelloServer" -> {
                MessageGroupIdentification messageGroupIdentification = new MessageGroupIdentification(jsonObject);
                if(messageGroupIdentification.isAI) {
                    System.out.println("Handle AI");
                }
                else {
                    System.out.println("Handle non AI");
                }
                if(!messageGroupIdentification.protocol.equals("Version 0.1")){
                    System.out.println("Handle false protocol Version");
                }
                System.out.println("Client from group " + messageGroupIdentification.group + "connected to group with protocol version "
                        + messageGroupIdentification.protocol);
            }
            case "PlayerValues" -> {
                MessageNameRequest messageSetName = new MessageNameRequest(jsonObject);
                System.out.println("Handle Name Request");
            }
            case "Alive" -> {
                MessageWelcome messageWelcome = new MessageWelcome(jsonObject);
                System.out.println("Client is still connected to server");
            }
            case "SetStatus" -> {
                MessageSetReady messageSetReady = new MessageSetReady(jsonObject);
                //player.setReady(messageReady.ready)
                if(messageSetReady.ready) {
                    System.out.println(client.name + " is ready");
                }
                else {
                    System.out.println(client.name + " is not ready");
                }
            }
            case "MapSelected" -> {
                MessageSelectedMap messageSelectedMap = new MessageSelectedMap(jsonObject);
                System.out.println("Set new Selected map: " + messageSelectedMap.map);
                //send MessageSelectedMap to every client
            }
            default -> {

            }
        }

    }
}
