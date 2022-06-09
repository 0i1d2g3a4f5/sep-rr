package newmessages;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * @author Sarp Cagin Erdogan
 */

public class Message{
    public String type;
    public JsonObject content;

    public Message(JsonObject jsonObject){
        type = jsonObject.get("MessageType").getAsString();
        content = jsonObject.get("MessageBody").getAsJsonObject();
    }
    //case Connection
    public Message(){

    }
    //case GroupIdentification
    public Message(String group, boolean isAI, String protocol) {

    }
    //case Welcome, case Player, case Phase, case Shuffle, case SelectionFinished
    public Message(int clientID) {

    }
    //case Protocol, case SelectedMap, case Error, case PlayCard
    public Message(String string){

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
    //case CardPlayed
    public Message(int clientID, String card){

    }
    //case SetStart, case Quantity, case SelectCard
    public Message(int x, int y) {

    }
    //case StartSet
    Message(int x, int y, int clientID){

    }
    //case Register
    Message(int x, int y, boolean filled){

    }


    public JsonObject toJSON(){
        JsonObject result = new JsonObject();
        result.add("MessageType", JsonParser.parseString(type));
        result.add("MessageBody", content);
        System.out.println("JSON Object of the message: " + this + " is: " + result);
        return result;
    }



}
