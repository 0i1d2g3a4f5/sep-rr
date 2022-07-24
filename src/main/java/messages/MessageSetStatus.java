package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_application.Task;
import server_application.TaskContent;
import server_application.TaskType;
import server_package.SClient;

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
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
            sClient.setReady(this.ready);
            sClient.getServer().getServerApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            sClient.sendAll(new MessagePlayerStatus(sClient.getId(), sClient.getIsReady()));
            if(sClient.getIsReady()){
                sClient.getServer().getReadyList().add(sClient);
                sClient.getServer().checkReady();
            }
            else{
                sClient.getServer().getReadyList().remove(sClient);
            }



    }

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
