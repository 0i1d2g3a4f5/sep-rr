package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */



public class MessageMovement extends Message{

    public int clientID;
    public int x;
    public int y;

    /**
     * converts message to json
     *
     * @param clientID
     * @param y
     * @param x
     * @author Isabel Muhm
     */
    public MessageMovement(int clientID, int y, int x){
        this.clientID = clientID;
        this.x = x;
        this.y = y;
        type = "Movement";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        //Server.serverLogger.info("Created Movement Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageMovement(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        x= content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        //Server.serverLogger.info("Created Movement Message: " + this + " from JSON: " + jsonObject);
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
     * moves robot of moving client to new position and updates gameboard to show movement in game scene
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Mark Ringer
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        if(clientID==client.getId()){
            client.getPlayer().getRobot().moveRobotTo(y, x);
        }
        else{
            client.playerFromId(clientID).getPlayer().getRobot().moveRobotTo(y, x);
        }
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD,new TaskContent()));
    }

    /**
     * moves robot of moving AI to new position
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            sentientClient.getPlayer().getRobot().moveRobotTo(y, x);
        }
        else{
            sentientClient.playerFromId(clientID).getPlayer().getRobot().moveRobotTo(y, x);
        }
    }
}
