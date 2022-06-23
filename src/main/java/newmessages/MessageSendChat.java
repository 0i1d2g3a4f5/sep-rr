package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm
 */

public class MessageSendChat extends Message{

    public String message;
    public int to;

    public MessageSendChat(String message, int to) {
        super(message, to);
        this.message = message;
        this.to = to;
        type = "SendChat";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", new JsonPrimitive(message));
        jsonObject.add("to", new JsonPrimitive(to));
        content = jsonObject;
        //System.out.println("Created Send Message: " + this);
    }

    public MessageSendChat(JsonObject jsonObject) {
        super(jsonObject);
        message = content.get("message").getAsString();
        to = content.get("to").getAsInt();
        //System.out.println("Created Send Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {
        if(to==-1){
            client.sendAll(new MessageReceivedChat(message, client.getId(), false));
        }
        else{
            /*
            if(client.getServer().clientFromId(messageSendChat.to)!=null) {
                client.sendSingle(client.server.clientFromId(messageSendChat.to), new MessageReceivedChat(messageSendChat.message, client.id, true));
            }
            else{
                client.sendSelf(new MessageError("ERROR :: Invalid private message recepient."));
            }

             */
        }
    }
}
