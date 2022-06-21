package newmessages;

import com.google.gson.*;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sarp Cagin Erdogan
 */

public abstract class Message{
    public String type;
    public JsonObject content;
    public MessageType messageType;

    private Server server;


    public Message(JsonObject jsonObject){
        type = jsonObject.get("MessageType").getAsString();
        messageType = MessageType.valueOf(type.toUpperCase());
        content = jsonObject.get("MessageBody").getAsJsonObject();
    }

    /**
     * @param server
     * @author Ringer
     */
    public abstract void activateMessage(Server server) throws IOException;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(toJSON());
    }

    //case Connection
    public Message(JsonArray jsonArray){

    }
    //case GroupIdentification
    public Message(String group, boolean isAI, String protocol) {

    }
    //case Welcome, case Player, case Phase, case Shuffle, case SelectionFinished, case RoboReboot, case Finish
    public Message(int clientID) {

    }
    //case Protocol, case SelectedMap, case Error, case PlayCard, case Animation, case RebootDirection
    public Message(String string){

    }
    public Message(String string1, String string2){

    }
    //case NameRequest, case MessageSend
    public Message(String name, int figure) {

    }
    //case NameSet
    public Message(int clientID, String name, int figure){

    }
    //case SetReady
    public Message(boolean ready){

    }
    //case Status
    public Message(int clientID, boolean ready){

    }
    //case AvailableMaps
    public Message(ArrayList<String> availableMaps) {

    }
    //case MessageReceived
    public Message(String message, int from, boolean isPrivate){

    }
    //case CardPlayed, case Turn
    public Message(int clientID, String card){

    }
    //case SetStart, case Quantity, case SelectCard, case CheckPoint
    public Message(int x, int y) {

    }
    //case StartSet, case Move
    Message(int x, int y, int clientID){

    }
    //case Register
    Message(int x, int y, boolean filled){

    }
    //case Energy
    Message(int clientID, int count, String source){

    }
    Message(){

    }

    public JsonObject toJSON(){
        JsonObject result = new JsonObject();
        result.add("MessageType", new JsonPrimitive(type));
        result.add("MessageBody", content);
        //System.out.println("JSON Object of the message: " + this + " is: " + result);
        return result;
    }



}
