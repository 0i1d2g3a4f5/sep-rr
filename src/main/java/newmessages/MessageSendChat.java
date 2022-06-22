package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;
import server_package.Server;


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
     * @param server
     */
    @Override
    public void activateMessage(Server server) {

        Client client = server.searchClient();
        if(to==-1){
            client.sendAll(new MessageReceivedChat(messageSendChat.message, client.id, false));
        }
        else{
            if(client.server.clientFromId(messageSendChat.to)!=null) {
                client.sendSingle(client.server.clientFromId(messageSendChat.to), new MessageReceivedChat(messageSendChat.message, client.id, true));
            }
            else{
                client.sendSelf(new MessageError("ERROR :: Invalid private message recepient."));
            }
        }

    }
}
