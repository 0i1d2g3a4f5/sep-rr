package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import javafx.util.Pair;
import newmessages.*;

import java.io.IOException;

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

                if(!messageGroupIdentification.protocol.equals("Version 0.1")){
                    System.out.println("Client " + client.id + " has the false communication protocol.");
                    //KICK OUT
                }
                else{
                    client.isAI=messageGroupIdentification.isAI;
                    client.group=messageGroupIdentification.group;
                    client.sendSelf(new MessageWelcome(client.id));
                    JsonObject temp = new JsonObject();
                    temp.add("ID", new JsonPrimitive(client.id));
                    client.server.application.addTask(new Task("AddToList", temp));
                }

            }
            case "HashedCode" -> {
                MessageHashedCode messageHashedCode = new MessageHashedCode(jsonObject);
                client.server.idAndHashFromName.put(client.name, new Pair<>(client.id, messageHashedCode.hashedCode));
                client.server.printList();
            }
            case "PlayerValues" -> {
                MessagePlayerValues messageValueRequest = new MessagePlayerValues(jsonObject);
                client.checkName(messageValueRequest.name, messageValueRequest.figure);


            }
            case "Alive" -> {
                //System.out.println("---");
                client.response=true;
            }
            case "Reconnect" -> {
                MessageReconnect messageReconnect = new MessageReconnect(jsonObject);
                System.out.println("Name: "+ messageReconnect.name + " Hash: " + messageReconnect.hash);
                client.checkExisting(messageReconnect.name, messageReconnect.hash);

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
