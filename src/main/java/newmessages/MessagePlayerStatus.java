package newmessages;

import client_application.Task;
import client_application.TaskBoolean;
import client_application.TaskContent;
import client_application.TaskType;
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
}
