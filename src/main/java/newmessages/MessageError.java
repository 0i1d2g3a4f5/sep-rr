package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
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
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            Client.clientLogger.info(this.error);
            if(this.error.equals("ERROR :: Figure already taken.")){
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.FIGURETAKEN, new TaskContent()));
            }
        } else{

        }

    }
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic){
        if(isBasic){
            System.out.println(this.error);
            if(this.error.equals("ERROR :: Figure already taken.")){
                if(client.getLastTriedFigure()>6){
                    System.out.println("NO MORE AVAILABLE FIGURES");
                }
                else {
                    client.setLastTriedFigure(client.getLastTriedFigure() + 1);
                    client.sendPlayerValues();
                }
            }
        } else{

        }
    }


    /**
     * @param server
     */

}
