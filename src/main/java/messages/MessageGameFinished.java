package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageGameFinished extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageGameFinished(int clientID){
        this.clientID =clientID;
        type = "GameFinished";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //Server.serverLogger.info("Created Game Finished Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageGameFinished(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //Server.serverLogger.info("Created Game Finished Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        //TODO trigger winscreen
        Server.serverLogger.info("Player " + clientID + " won");
        if(clientID==client.getId()){
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.WIN, new TaskContent()));
        } else {
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.LOSE, new TaskContent()));
        }


    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
