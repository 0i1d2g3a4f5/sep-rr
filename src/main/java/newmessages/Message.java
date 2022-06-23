package newmessages;

import com.google.gson.*;
import server_package.Client;
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
        messageType = new MessageTypeFactory().fromString(type);
        content = jsonObject.get("MessageBody").getAsJsonObject();
    }

    /**
     * @param client
     * @param isBasic
     * @author Ringer, Erdogan
     */
    public abstract void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException;
    public abstract void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(toJSON());
    }

    //case Connection
    Message(JsonArray jsonArray){

    }
    //case GroupIdentification
    Message(String group, boolean isAI, String protocol) {

    }
    //case Welcome, case Player, case Phase, case Shuffle, case SelectionFinished, case RoboReboot, case Finish
    Message(int clientID) {

    }
    //case Protocol, case SelectedMap, case Error, case PlayCard, case Animation, case RebootDirection
    Message(String string){

    }
    Message(String string1, String string2){

    }
    //case NameRequest, case MessageSend
    Message(String name, int figure) {

    }
    //case NameSet
    Message(int clientID, String name, int figure){

    }
    //case SetReady
    Message(boolean ready){

    }
    //case Status
    Message(int clientID, boolean ready){

    }
    //case AvailableMaps
    Message(ArrayList<String> availableMaps) {

    }
    //case MessageReceived
    Message(String message, int from, boolean isPrivate){

    }
    //case CardPlayed, case Turn
    Message(int clientID, String card){

    }
    //case SetStart, case Quantity, case SelectCard, case CheckPoint
    Message(int x, int y) {

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
        return result;
    }



}
