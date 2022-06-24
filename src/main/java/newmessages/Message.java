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
        type = jsonObject.get("messageType").getAsString();
        messageType = new MessageTypeFactory().fromString(type);
        content = jsonObject.get("messageBody").getAsJsonObject();
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

    Message(){

    }

    public JsonObject toJSON(){
        JsonObject result = new JsonObject();
        result.add("messageType", new JsonPrimitive(type));
        result.add("messageBody", content);
        return result;
    }



}
