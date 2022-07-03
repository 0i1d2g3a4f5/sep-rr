package client_package.clientSideMessages;

import client_package.AI.AIClient;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */

public abstract class Message{
    public String type;
    public JsonObject content;
    public MessageType messageType;



    public Message(JsonObject jsonObject){
        type = jsonObject.get("messageType").getAsString();
        messageType = new MessageTypeFactory().fromString(type);
        content = jsonObject.get("messageBody").getAsJsonObject();
    }



    public abstract void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException;
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        activateMessageInFrontend(client,isBasic);
    }

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
