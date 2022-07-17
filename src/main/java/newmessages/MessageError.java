package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageError extends Message{

    public String error;

    /**
     * @param error
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
     * @param jsonObject
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

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            Client.clientLogger.info(this.error);
            if(this.error.equals("ERROR :: Figure already taken.")) {
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.FIGURETAKEN, new TaskContent()));
            }


    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(this.error.equals("Figure is already taken")) {
            sentientClient.retryFigure();
        }
    }


    /**
     * @param server
     */

}
