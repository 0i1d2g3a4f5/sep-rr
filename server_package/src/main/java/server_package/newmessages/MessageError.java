package server_package.newmessages;

import client_package.client_application.Task;
import client_package.client_application.TaskContent;
import client_package.client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

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
        //System.out.println("Created Error Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageError(JsonObject jsonObject) {
        super(jsonObject);
        error = content.get("error").getAsString();
        //System.out.println("Created Error Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            System.out.println(this.error);
            if(this.error.equals("ERROR :: Figure already taken.")){
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.FIGURETAKEN, new TaskContent()));
            }
        } else{

        }

    }


    /**
     * @param server
     */

}
