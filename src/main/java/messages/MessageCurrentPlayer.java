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


public class MessageCurrentPlayer extends Message{

    public int clientID;

    /**
     * converts message to json
     *
     * @param clientID
     * @author Isabel Muhm
     */
    public MessageCurrentPlayer(int clientID) {
        this.clientID = clientID;
        type = "CurrentPlayer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        Server.serverLogger.info("Created CurrentPlayer Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageCurrentPlayer(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //Server.serverLogger.info("Created CurrentPlayer Message: " + this + " from JSON: " + jsonObject);
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
     * activates starting point chose in game scene
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        if(clientID==client.getId()){
            if(client.getGame().getPhase()==0){
                client.getClientApplication().waitForGame();
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.CHOOSE_STARTING_POINT, new TaskContent()));
            }
        }
    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            if(sentientClient.getGame().getPhase()==0){
                sentientClient.chooseStartingPoint();
            }
        }
    }
}
