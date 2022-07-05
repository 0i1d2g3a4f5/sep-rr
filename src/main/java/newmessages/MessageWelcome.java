package newmessages;

import client_application.Task;
import client_application.TaskInt1;
import client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWelcome extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageWelcome(int clientID) {
        this.clientID = clientID;
        type = "Welcome";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //System.out.println("Created Welcome Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageWelcome(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
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

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.GOTID, new TaskInt1(this.clientID)));
        }else{

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

            client.setId(clientID);
            client.sendSelf(new MessagePlayerValues(client.getName(), ThreadLocalRandom.current().nextInt(1,7)));
        }else{

        }

    }
}
