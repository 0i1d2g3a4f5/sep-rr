package newmessages;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public Message(String string){

    }

    public JsonObject toJSON(){
        JsonObject result = new JsonObject();
        result.add("MessageType", JsonParser.parseString(type));
        result.add("MessageBody", content);
        System.out.println("JSON Object of the message: " + this + " is: " + result);
        return result;
    }



}
