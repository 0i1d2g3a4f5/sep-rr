package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

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
        //Server.serverLogger.info("Created Set Status Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSetStatus(JsonObject jsonObject){
        super(jsonObject);
        ready = content.get("ready").getAsBoolean();
        //Server.serverLogger.info("Created Set Status Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            sClient.setReady(this.ready);
            sClient.sendAll(new MessagePlayerStatus(sClient.getId(), sClient.getIsReady()));
            if(sClient.getIsReady()){
                sClient.getServer().getReadyList().add(sClient);
                sClient.getServer().checkReady();
            }
            else{
                sClient.getServer().getReadyList().remove(sClient);
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
