package messages;

import client_application.Task;
import client_application.TaskJsonArray;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server_package.SClient;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageSelectMap extends Message{

    public JsonArray availableMaps;

    /**
     * turn message to json
     *
     * @param jsonArray
     */
    public MessageSelectMap(JsonArray jsonArray){
        type = "SelectMap";
        this.availableMaps = jsonArray;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("availableMaps", jsonArray);
        content=jsonObject;
    }

    /**
     * turn json to message
     *
     * @param jsonObject
     */
    public MessageSelectMap(JsonObject jsonObject){
        super(jsonObject);
        availableMaps=content.get("availableMaps").getAsJsonArray();
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {}

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
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
