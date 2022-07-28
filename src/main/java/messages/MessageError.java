package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import utility.GlobalParameters;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageError extends Message{

    public String error;

    /**
     * converts message to json
     *
     * @param error
     * @author Isabel Muhm
     */
    public MessageError(String error){
        this.error = error;
        type = "Error";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", new JsonPrimitive(error));
        content = jsonObject;
        //Server.serverLogger.info("Created Error Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageError(JsonObject jsonObject) {
        super(jsonObject);
        error = content.get("error").getAsString();
        //Server.serverLogger.info("Created Error Message: " + this + " from JSON: " + jsonObject);
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
     * creates task to inform client about current error
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            Client.clientLogger.info(this.error);
            if(this.error.equals(GlobalParameters.FIGURE_ERROR)) {
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.FIGURETAKEN, new TaskContent()));
            }
            if(this.error.equals(GlobalParameters.STARTING_POINT_TAKEN_ERROR)) {
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.STARTING_POINT_NOT_AVAILABLE, new TaskContent()));
            }
        if(this.error.equals(GlobalParameters.STARTING_POINT_INVALID_ERROR)) {
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.STARTING_POINT_INVALID, new TaskContent()));
        }


    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(this.error.equals(GlobalParameters.FIGURE_ERROR)) {
            sentientClient.getSentientBehaviour().retryFigure();
        }
        if(this.error.equals(GlobalParameters.STARTING_POINT_TAKEN_ERROR)) {
            sentientClient.getSentientBehaviour().chooseStartingPoint();
        }
    }


    /**
     * @param server
     */

}
