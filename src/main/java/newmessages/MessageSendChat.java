package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageSendChat extends Message{

    public String message;
    public int to;

    public MessageSendChat(String message, int to) {
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
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.to == -1) {
                client.sendAll(new MessageReceivedChat(this.message, client.getId(), false));
            } else {
                if (client.getServer().clientFromID(this.to) != null) {
                    client.sendSingle(client.getServer().clientFromID(this.to), new MessageReceivedChat(this.message, client.getId(), true));
                } else {
                    client.sendSelf(new MessageError("ERROR :: Invalid private message recepient."));
                }
            }
        }
        else {
            //ADVANCED
        }

        /*
        if(to==-1){
            client.sendAll(new MessageReceivedChat(message, client.getId(), false));
        }
        else{

            if(client.getServer().clientFromId(messageSendChat.to)!=null) {
                client.sendSingle(client.server.clientFromId(messageSendChat.to), new MessageReceivedChat(messageSendChat.message, client.id, true));
            }
            else{
                client.sendSelf(new MessageError("ERROR :: Invalid private message recepient."));
            }

             */
        }


    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
