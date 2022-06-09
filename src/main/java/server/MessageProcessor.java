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
                MessageHelloServer messageGroupIdentification = new MessageHelloServer(jsonObject);
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
                MessagePlayerValues messageValueRequest = new MessagePlayerValues(jsonObject);
                System.out.println("Handle Name Request");
            }
            case "Alive" -> {
                MessageAlive messageAlive = new MessageAlive(jsonObject);
                System.out.println("Clients sends to be still connected to server");
            }
            case "SetStatus" -> {
                MessageSetStatus messageSetReady = new MessageSetStatus(jsonObject);
                //player.setReady(messageReady.ready)
                if(messageSetReady.ready) {
                    System.out.println(client.name + " is ready");
                }
                else {
                    System.out.println(client.name + " is not ready");
                }
            }
            case "MapSelected" -> {
                MessageMapSelected messageSelectedMap = new MessageMapSelected(jsonObject);
                System.out.println("Set new Selected map: " + messageSelectedMap.map);
                //send MessageSelectedMap to every client
            }
            case "SendChat" -> {
                MessageSendChat messageSend = new MessageSendChat(jsonObject);
                if(messageSend.to == -1){
                    System.out.println("Handle message to all");
                }
                else {
                    System.out.println("Handle message to specific client");
                }
            }
            case "PlayCard" -> {
                MessagePlayCard messagePlayCard = new MessagePlayCard(jsonObject);
                System.out.println("Handle Played Card");
                //handle card & send CardPlayed Message to all Clients
            }
            case "SetStartingPoint" ->{
                MessageSetStartingPoint messageStartRequest = new MessageSetStartingPoint(jsonObject);
                System.out.println("Handle StartingPoint Request");
            }
            case "SelectedCard" -> {
                MessageSelectedCard messageSelectCard = new MessageSelectedCard(jsonObject);
                if(messageSelectCard.card == null) {
                    System.out.println("clear register");
                }
                else{
                    System.out.println("fill register " + messageSelectCard.register + " with card: " + messageSelectCard.card);
                }
                //count amount of selected cards, if a client has 5 no changes are possible and SelectionFinished Message will be send
            }
            //not sure if this case belongs to server or client
            case "SelectionFinished" -> {
                MessageSelectionFinished messageSelectionFinished = new MessageSelectionFinished(jsonObject);
                System.out.println("Dont let player " + messageSelectionFinished.clientID +" select any other cards");
            }
            case "RebootDirection" -> {
                MessageRebootDirection messageRebootDirection = new MessageRebootDirection(jsonObject);
                if(messageRebootDirection.direction.equals("top")) {
                    System.out.println("Handle top rotation");
                }
                else if(messageRebootDirection.direction.equals("right")) {
                    System.out.println("Handle right rotation");
                }
                else if(messageRebootDirection.direction.equals("bottom")) {
                    System.out.println("Handle bottom rotation");
                }
                else if(messageRebootDirection.direction.equals("left")) {
                    System.out.println("Handle left rotation");
                }
            }
            //not sure if this case belongs to server or client
            case "CheckPointReached" -> {
                MessageCheckPointReached messageCheckPoint = new MessageCheckPointReached(jsonObject);
                System.out.println("Handle CheckPoint " + messageCheckPoint.number +" reach");
            }
            default -> {
            //error Message?
            }
        }

    }
}
