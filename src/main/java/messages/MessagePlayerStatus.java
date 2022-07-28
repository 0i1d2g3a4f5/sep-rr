package messages;

import client_application.Task;
import client_application.TaskBoolean;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.CPlayer;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerStatus extends Message {

    public int clientID;
    public boolean ready;

    /**
     * converts message to json
     *
     * @param clientID
     * @param ready
     * @author Isabel Muhm
     */
    public MessagePlayerStatus(int clientID, boolean ready){
        this.clientID = clientID;
        this.ready = ready;
        type = "PlayerStatus";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("ready", new JsonPrimitive(ready));
        content = jsonObject;
        //Server.serverLogger.info("Created Player Status Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessagePlayerStatus(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        ready = content.get("ready").getAsBoolean();
        //Server.serverLogger.info("Created Player Status Message: " + this + " from JSON: " + jsonObject);
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
     * sets status of given client to ready and creates task to update lobbylist in lobby scene
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            if(this.clientID==client.getId()) {
                client.setIsReady(this.ready);
                client.setPlayer(new CPlayer(client, true));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEREADYBUTTON, new TaskBoolean(this.ready)));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
            else{
                client.handleReady(this.ready, this.clientID);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
    }

    /**
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            sentientClient.setIsReady(ready);
            if(sentientClient.isReady()){
                sentientClient.setPlayer(new CPlayer(sentientClient, true));
            }
            else{
                sentientClient.setPlayer(null);
            }
        }
        else{
            sentientClient.handleReady(ready, clientID);
        }

    }
}
