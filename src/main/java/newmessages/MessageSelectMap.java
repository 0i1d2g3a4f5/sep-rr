package newmessages;

import client_application.Task;
import client_application.TaskJsonArray;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageSelectMap extends Message{

    public JsonArray availableMaps;

    /**
     * @param jsonArray
     */
    public MessageSelectMap(JsonArray jsonArray){
        type = "SelectMap";
        this.availableMaps = jsonArray;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("availableMaps", jsonArray);
        content=jsonObject;
        //Server.serverLogger.info("Created Select Map Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSelectMap(JsonObject jsonObject){
        super(jsonObject);
        availableMaps=content.get("availableMaps").getAsJsonArray();
        //Server.serverLogger.info("Created Select Map Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.AVAILABLEMAPS, new TaskJsonArray(this.availableMaps)));

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }

}
