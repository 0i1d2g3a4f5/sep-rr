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

public class MessageStartingPointTaken extends Message{

    public int x;
    public int y;
    public int clientID;

    /**
     * @param x
     * @param y
     * @param clientID
     */
    public MessageStartingPointTaken(int x, int y, int clientID){
        this.x = x;
        this.y = y;
        this.clientID = clientID;
        type = "StartingPointTaken";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //Server.serverLogger.info("Created Starting Point Taken Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageStartingPointTaken(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x"). getAsInt();
        y = content.get("y"). getAsInt();
        clientID = content.get("clientID"). getAsInt();
        //Server.serverLogger.info("Created Starting Point Taken Message: " + this + " from JSON: " + jsonObject);
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
     * @author Mark Ringer
     * places the Robot and updates the Map
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        if(clientID==client.getId()){
            client.getPlayer().getRobot().placeRobot(y, x);
            client.getClientApplication().getGameController().visibilityStartingChoose(false);
            client.getClientApplication().getGameController().visibilityPlayerDetails(true);
        }
        else{
            client.playerFromId(clientID).getPlayer().getRobot().placeRobot(y, x);
        }
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));



        /*if(client.getId()==clientID) {
            if(!client.getPlayer().getRobot().isPlaced()) {
                client.getPlayer().getRobot().placeRobot(y, x);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));
                //TODO disable place Robot Field
            }
        } else {
            Client.clientLogger.info("Setting other robot");
            for (CPlayer player: client.getGame().getPlayerList()) {
                Client.clientLogger.info("Checking client:" + player.getClientID());
                if(player.getClientID() == clientID && clientID !=client.getId()){
                    Client.clientLogger.info("Figure: " +player.getRobot().getFigure());

                    player.getRobot().placeRobot(y,x);
                    client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));


                }

            }
        }*/

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            sentientClient.getPlayer().getRobot().placeRobot(y, x);
        }
        else{
            sentientClient.playerFromId(clientID).getPlayer().getRobot().placeRobot(y, x);
        }
    }

}
