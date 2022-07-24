package messages;

import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import server_package.SClient;
import server_package.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */

public abstract class Message{
    public static Logger messageLogger = Logger.getLogger("Message");
    static {
        try {
            Properties properties= new Properties();
            properties.load(new FileInputStream("log4j.properties"));
            PropertyConfigurator.configure(properties);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String type;
    public JsonObject content;
    public MessageType messageType;
    private Server server;
    public Message(JsonObject jsonObject){

        type = jsonObject.get("messageType").getAsString();
        messageType = new MessageTypeFactory().fromString(type);
        content = jsonObject.get("messageBody").getAsJsonObject();
        /*messageLogger.info("New message fromJson: \n" +
                "Type="+type + "\n" +
                "Content="+content);*/
    }
    public abstract void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException;
    public abstract void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException;
    public abstract void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException;
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(toJSON());
    }

    Message(String type){
        this.type = type;
        messageLogger.info("New message created: " + type);
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
