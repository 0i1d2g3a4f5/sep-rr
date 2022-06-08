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
    //case Welcome
    public Message(int clientID) {

    }
    //case Protocol, case SelectedMap
    public Message(String string){

    }
    //case NameRequest
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


    public JsonObject toJSON(){
        JsonObject result = new JsonObject();
        result.add("MessageType", JsonParser.parseString(type));
        result.add("MessageBody", content);
        System.out.println("JSON Object of the message: " + this + " is: " + result);
        return result;
    }



}
