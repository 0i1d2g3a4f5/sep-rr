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
        switch (message.type){
            case "HelloClient" -> {
                MessageHelloClient messageProtocol = new MessageHelloClient(jsonObject);
                if(messageProtocol.protocol.equals("Version 0.1")){
                    System.out.println("Correct communication protocol verified.");
                    client.sendSelf(new MessageHelloServer(client.group, false, "Version 0.1"));
                    client.application.addTask(new Task("SwitchToName",""));
                }
                else{
                    System.out.println("Error: False communication protocol.");
                    client.sendSelf(new MessageHelloServer(client.group, false, "Version 0.1"));
                    //SHUTDOWN
                }

            }
            case "NameUnavailable" -> {
                MessageNameUnavailable messageNameUnavailable = new MessageNameUnavailable(jsonObject);
                client.application.addTask(new Task("NameUnavailable", messageNameUnavailable.name));

            }
            case "FigureUnavailable" -> {
                MessageFigureUnavailable messageFigureUnavailable = new MessageFigureUnavailable(jsonObject);
                client.application.addTask(new Task("NameUnavailable", String.valueOf(messageFigureUnavailable.figure)));

            }
            case "ValuesAccepted" -> {
                MessageValuesAccepted messageValuesAccepted = new MessageValuesAccepted(jsonObject);
                client.name=messageValuesAccepted.name;
                client.figure=messageValuesAccepted.figure;
                client.sendSelf(new MessageHashedCode(client.application.nameController.pass));
            }
            case "Welcome" -> {
                MessageWelcome messageWelcome = new MessageWelcome(jsonObject);
                client.id=messageWelcome.clientID;
            }
            case "PlayerAdded" -> {
                MessagePlayerAdded messageValueSet = new MessagePlayerAdded(jsonObject);
                System.out.println("Name " + messageValueSet.name + " and figure number " + messageValueSet.figure + " set");
            }
            case "Alive" -> {
                System.out.println("---");
                client.sendSelf(new MessageAlive());
            }
            case "PlayerStatus" -> {
                MessagePlayerStatus messageStatus = new MessagePlayerStatus(jsonObject);
                if(messageStatus.ready) {
                    System.out.println("Player with clientID " + messageStatus.clientID + " is ready");
                }
                else {
                    System.out.println("Player with clientID " + messageStatus.clientID + " is not ready");
                }
            }
            case "MapSelected" -> {
                MessageMapSelected messageSelectedMap = new MessageMapSelected(jsonObject);
                System.out.println("New Selected map: " + messageSelectedMap.map + "was set");
            }
            case "ReceivedChat" -> {
                MessageReceivedChat messageReceived = new MessageReceivedChat(jsonObject);
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
                MessagePlayedCard messageCardPlayed = new MessagePlayedCard(jsonObject);
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
                MessageCurrentPlayer messagePlayer = new MessageCurrentPlayer(jsonObject);
                if(messagePlayer.clientID == client.id){
                    System.out.println("Handle own turn");
                }
                else {
                    System.out.println("register other turn");
                }
            }
            case "ActivePhase" -> {
                MessageActivePhase messagePhase = new MessageActivePhase(jsonObject);
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
                MessageStartingPointTaken messageStartSet = new MessageStartingPointTaken(jsonObject);
                if(messageStartSet.clientID == client.id) {
                    System.out.println("Set StartingPoint & LobbyMessage \"You got StartingPoint ...\" & GUI");
                }
                else {
                    System.out.println("Handle StartSet Message (LobbyChat & GUI)");
                }
            }
            case "NotYourCards" -> {
                MessageNotYourCards messageQuantity = new MessageNotYourCards(jsonObject);
                if(messageQuantity.clientID == client.id){
                    System.out.println("Handle own quantity");
                }
                else {
                    System.out.println("Handle other quantity");
                }
            }
            case "ShuffleCoding" -> {
                MessageShuffleCoding messageShuffle = new MessageShuffleCoding(jsonObject);
                System.out.println("Handle shuffle request");
            }
            case "CardSelected" -> {
                MessageCardSelected messageRegister = new MessageCardSelected(jsonObject);
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
                MessageTimerEnded messageStartTime = new MessageTimerEnded(jsonObject);
                System.out.println("Start Timer");
            }
            case "TimerEnded" -> {
                MessageTimerStarted messageEndTime = new MessageTimerStarted(jsonObject);
                System.out.println("End Timer and handle slow players");
            }
            case "Movement" -> {
                MessageMovement messageMove = new MessageMovement(jsonObject);
                if(messageMove.clientID == client.id){
                    System.out.println("Handle own Movement");
                }
                else {
                    System.out.println("Handle foreign movement");
                }
            }
            case "PlayerTurning" -> {
                MessagePlayerTurning messageTurn = new MessagePlayerTurning(jsonObject);
                if(messageTurn.clientID == client.id){
                    if(messageTurn.rotation.equals("clockwise")) {
                        System.out.println("Handle own clockwise Turn");
                    }
                    else if(messageTurn.rotation.equals("counterclockwise")) {
                        System.out.println("Handle own counterclockwise Turn");
                    }
                }
                else {
                    if(messageTurn.rotation.equals("clockwise")) {
                        System.out.println("Handle foreign clockwise Turn");
                    }
                    else if(messageTurn.rotation.equals("counterclockwise")) {
                        System.out.println("Handle foreign counterclockwise Turn");
                    }
                }
            }
            case "Animation" -> {
                MessageAnimation messageAnimation = new MessageAnimation(jsonObject);
                if(messageAnimation.type.equals("BlueConveyorBelt")){
                    System.out.println("Handle BlueConveyorBelt Animation");
                }
                else if(messageAnimation.type.equals("GreenConveyorBelt")){
                    System.out.println("Handle GreenConveyorBelt Animation");
                }
                else if(messageAnimation.type.equals("PushPanel")){
                    System.out.println("Handle PushPanel Animation");
                }
                else if(messageAnimation.type.equals("Gear")){
                    System.out.println("Handle Gear Animation");
                }
                else if(messageAnimation.type.equals("CheckPoint")){
                    System.out.println("Handle CheckPoint Animation");
                }
                else if(messageAnimation.type.equals("PlayerShooting")){
                    System.out.println("Handle PlayerShooting Animation");
                }
                else if(messageAnimation.type.equals("WallShooting")){
                    System.out.println("Handle WallShooting Animation");
                }
                else if(messageAnimation.type.equals("EnergySpace")){
                    System.out.println("Handle EnergySpace Animation");
                }
            }
            case "Reboot" -> {
                MessageReboot messageRoboReboot = new MessageReboot(jsonObject);
                if(messageRoboReboot.clientID == client.id){
                    System.out.println("Handle own reboot (send RebootDirection Message to Server and switch to chosen direction)");
                }
                else {
                    System.out.println("Handle foreign reboot");
                }
            }
            case "Energy" -> {
                MessageEnergy messageEnergy = new MessageEnergy(jsonObject);
                System.out.println("Handle Energy from " + messageEnergy.source);
            }
            //not sure if this case belongs to server or client
            case "CheckPointReached" -> {
                MessageCheckPointReached messageCheckPoint = new MessageCheckPointReached(jsonObject);
                System.out.println("Handle CheckPoint " + messageCheckPoint.number + " reach");
            }
            case "GameFinished" -> {
                MessageGameFinished messageFinish = new MessageGameFinished(jsonObject);
                System.out.println("Handle Winner");
            }
            default -> {

            }
        }

    }
}
