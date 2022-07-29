package messages;

import client_application.Task;
import client_application.TaskInt1;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWelcome extends Message{

    public int clientID;

    /**
     * converts message to json
     *
     * @param clientID
     */
    public MessageWelcome(int clientID) {
        this.clientID = clientID;
        type = "Welcome";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
    }

    /**
     * converts json to message
     * @param jsonObject
     */
    public MessageWelcome(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
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
     * creates task to document own id
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.GOTID, new TaskInt1(this.clientID)));

    }

    /**
     * documents own ID
     *
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.setId(clientID);
        sentientClient.getSentientBehaviour().sendOwnInfo();
    }

}
