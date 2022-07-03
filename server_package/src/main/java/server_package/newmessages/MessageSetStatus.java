package server_package.newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageSetStatus extends Message{

    public boolean ready;

    /**
     * @param ready
     */
    public MessageSetStatus(boolean ready) {
        this.ready = ready;
        type = "SetStatus";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        System.out.println("Created SetReady Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSetStatus(JsonObject jsonObject){
        super(jsonObject);
        ready = content.get("ready").getAsBoolean();
        System.out.println("Created SetReady Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            client.setReady(this.ready);
            client.sendAll(new MessagePlayerStatus(client.getId(), client.getIsReady()));
            if(client.getIsReady()){
                client.getServer().getReadyList().add(client);
                client.getServer().checkReady();
            }
            else{
                client.getServer().getReadyList().remove(client);
            }

        }else{

        }

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

}
