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
                //optimalerweise werden in diesem case auch die bereits verbundenen Clients und deren Status übergeben
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
            case "ReceivedChat" -> {
                MessageReceived messageReceived = new MessageReceived(jsonObject);
                if (messageReceived.isPrivate) {
                    System.out.println("Handle private Message (private Chat)");
                }
                else {
                    System.out.println("Handle non private Message (LobbyChat)");
                }
            }
            case "Error" -> {
                MessageError messageError = new MessageError(jsonObject);
                System.out.println("Handle error message");
            }
            case "CardPlayed" -> {
                MessageCardPlayed messageCardPlayed = new MessageCardPlayed(jsonObject);
                //check if clientId equals my own ID -> then show other message?
                if(messageCardPlayed.clientID == client.id) {
                    System.out.println("\"You played \"" + messageCardPlayed.card);
                }
                else {
                    System.out.println(messageCardPlayed.clientID + "\" played \"" + messageCardPlayed.card);
                }
                System.out.println("Handle CardPlayed Message (LobbyChat)");
            }
            case "CurrentPlayer" -> {
                MessagePlayer messagePlayer = new MessagePlayer(jsonObject);
                if(messagePlayer.clientID == client.id){
                    System.out.println("Handle own turn");
                }
                else {
                    System.out.println("register other turn");
                }
            }
            case "ActivePhase" -> {
                MessagePhase messagePhase = new MessagePhase(jsonObject);
                if(messagePhase.phase == 0){
                    System.out.println("Handle Aufbauphase");
                }
                else if(messagePhase.phase == 1){
                    System.out.println("Handle upgrade phase");
                }
                else if(messagePhase.phase == 2){
                    System.out.println("Handle programming phase");
                }
                else if(messagePhase.phase == 3){
                    System.out.println("Handle activation phase");
                }
            }
            case "StartingPointTaken" -> {
                MessageStartSet messageStartSet = new MessageStartSet(jsonObject);
                if(messageStartSet.clientID == client.id) {
                    System.out.println("Set StartingPoint & LobbyMessage \"You got StartingPoint ...\" & GUI");
                }
                else {
                    System.out.println("Handle StartSet Message (LobbyChat & GUI)");
                }
            }
            case "NotYourCards" -> {
                MessageQuantity messageQuantity = new MessageQuantity(jsonObject);
                if(messageQuantity.clientID == client.id){
                    System.out.println("Handle own quantity");
                }
                else {
                    System.out.println("Handle other quantity");
                }
            }
            case "ShuffleCoding" -> {
                MessageShuffle messageShuffle = new MessageShuffle(jsonObject);
                System.out.println("Handle shuffle request");
            }
            case "CardSelected" -> {
                MessageRegister messageRegister = new MessageRegister(jsonObject);
                //erster fall evtl nicht nötig, da davon ausgegegangen wird das register auf jeden Fall belegt wird und keine Rückmeldung erforderlich ist
                //wenn dann nur für andere LobbyMessage
                if(messageRegister.clientID == client.id) {
                    if(messageRegister.filled){
                        System.out.println("handle filled register "+  messageRegister.register);
                    }
                    else {
                        System.out.println("handle emptied register");
                    }
                }
                else {
                    if(messageRegister.filled){
                        System.out.println("inform about filled register "+  messageRegister.register);
                    }
                    else {
                        System.out.println("inform about emptied register");
                    }
                }
            }
            //not sure if this case belongs to server or client
            case "SelectionFinished" -> {
                MessageSelectionFinished messageSelectionFinished = new MessageSelectionFinished(jsonObject);
                System.out.println("Dont let player " + messageSelectionFinished.clientID +" select any other cards");
            }
            case "TimerStarted" -> {
                MessageStartTime messageStartTime = new MessageStartTime(jsonObject);
                System.out.println("Start Timer");
            }
            default -> {

            }
        }

    }
}
