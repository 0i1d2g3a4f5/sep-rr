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
        messageType = MessageType.valueOf(type.toUpperCase());
        content = jsonObject.get("MessageBody").getAsJsonObject();
    }

    /**
     * @param client
     * @author Ringer
     */
    public abstract void activateMessage(Client client) throws IOException, ClientNotFoundException, InterruptedException;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(toJSON());
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
