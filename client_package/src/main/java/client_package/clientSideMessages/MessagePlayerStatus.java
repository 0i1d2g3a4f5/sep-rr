package client_package.clientSideMessages;

import client_package.client_application.Task;
import client_package.client_application.TaskBoolean;
import client_package.client_application.TaskContent;
import client_package.client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerStatus extends Message {

    public int clientID;
    public boolean ready;

    /**
     * @param clientID
     * @param ready
     */
    public MessagePlayerStatus(int clientID, boolean ready){
        this.clientID = clientID;
        this.ready = ready;
        type = "PlayerStatus";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        //System.out.println("Created Status Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerStatus(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        ready = content.get("ready").getAsBoolean();
        //System.out.println("Created Status Message: " + this + " from JSON: " + jsonObject);
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

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            if(this.clientID==client.getId()) {
                client.setIsReady(this.ready);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEREADYBUTTON, new TaskBoolean(this.ready)));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
            else{
                client.clientFromId(this.clientID).setIsReady(this.ready);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
        }
        else{

        }

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            if(this.clientID==client.getId()) {
                client.setIsReady(this.ready);
                boolean isFirst = false;
            }
            else{
                client.clientFromId(this.clientID).setIsReady(this.ready);

            }
        }
        else{

        }

    }
}
