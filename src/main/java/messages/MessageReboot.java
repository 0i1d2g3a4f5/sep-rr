package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageReboot extends Message{

    public int clientID;

    /**
     * converts message to json
     *
     * @param clientID
     */
    public MessageReboot(int clientID){
        this.clientID = clientID;
        type = "Reboot";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     */
    public MessageReboot(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
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
        if(clientID == client.getId())
             client.getClientApplication().addAndExecuteTask(new Task(TaskType.REBOOTDIRECTION, new TaskContent()));
    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID == sentientClient.getId()){
            sentientClient.getSentientBehaviour().chooseRebootDirection();
        }
    }
}
