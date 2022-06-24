package newmessages;

import client_application.Task;
import client_application.TaskInt1;
import client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */
public class MessageWelcome extends Message{

    public int clientID;

    public MessageWelcome(int clientID) {
        this.clientID = clientID;
        type = "Welcome";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //System.out.println("Created Welcome Message: " + this);
    }

    public MessageWelcome(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
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
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.GOTID, new TaskInt1(this.clientID)));
        }else{

        }

    }
}
