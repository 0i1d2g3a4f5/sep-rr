package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gamelogic.Position;
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
     * converts message to json
     *
     * @param x
     * @param y
     * @param clientID
     * @author Isabel MUhm
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
    }

    /**
     * converts json to message
     * @param jsonObject
     */
    public MessageStartingPointTaken(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x"). getAsInt();
        y = content.get("y"). getAsInt();
        clientID = content.get("clientID"). getAsInt();
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
        client.setThereIsStartingToRemove(true);
        client.removeStartingPoint(new Position(y, x));
        client.getHighSlumber().add(new Position(y, x));
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARDPARTS, new TaskContent()));
    }

    /**
     * playes robot
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            sentientClient.getPlayer().getRobot().placeRobot(y, x);
        }
        else{
            while(sentientClient.playerFromId(clientID)==null){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            sentientClient.playerFromId(clientID).getPlayer().getRobot().placeRobot(y, x);
            sentientClient.setThereIsStartingToRemove(true);
            sentientClient.removeStartingPoint(new Position(y, x));
        }
    }

}
