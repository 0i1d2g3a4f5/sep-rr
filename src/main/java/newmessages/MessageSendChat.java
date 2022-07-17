package newmessages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageSendChat extends Message{

    public String message;
    public int to;

    /**
     * @param message
     * @param to
     */
    public MessageSendChat(String message, int to) {
        this.message = message;
        this.to = to;
        type = "SendChat";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", new JsonPrimitive(message));
        jsonObject.add("to", new JsonPrimitive(to));
        content = jsonObject;
        //Server.serverLogger.info("Created Send Chat Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSendChat(JsonObject jsonObject) {
        super(jsonObject);
        message = content.get("message").getAsString();
        to = content.get("to").getAsInt();
        //Server.serverLogger.info("Created Send Chat Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
            if (this.to == -1) {
                sClient.sendAll(new MessageReceivedChat(this.message, sClient.getId(), false));
            } else {
                if (sClient.getServer().clientFromID(this.to) != null) {
                    sClient.sendSingle(sClient.getServer().clientFromID(this.to), new MessageReceivedChat(this.message, sClient.getId(), true));
                } else {
                    Server.serverLogger.error("Invalid private message recipient");
                    sClient.sendSelf(new MessageError("ERROR :: Invalid private message recipient."));
                }
            }
        }

        //TODO - what is this?

        /*
        if(to==-1){
            sClient.sendAll(new MessageReceivedChat(message, sClient.getId(), false));
        }
        else{

            if(sClient.getServer().clientFromId(messageSendChat.to)!=null) {
                sClient.sendSingle(sClient.server.clientFromId(messageSendChat.to), new MessageReceivedChat(messageSendChat.message, sClient.id, true));
            }
            else{
                sClient.sendSelf(new MessageError("ERROR :: Invalid private message recipient."));
            }

             */


    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
