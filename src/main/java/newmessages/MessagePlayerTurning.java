package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.CPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerTurning extends Message{

    public int clientID;
    public String rotation;

    /**
     * @param clientID
     * @param rotation
     */
    public MessagePlayerTurning(int clientID, String rotation){

        this.clientID = clientID;
        this.rotation = rotation;
        type = "PlayerTurning";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("rotation", new JsonPrimitive(rotation));
        content = jsonObject;
        //Server.serverLogger.info("Created Player Turning Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerTurning(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        rotation = content.get("rotation").getAsString();
        //Server.serverLogger.info("Created Player Turning Message: " + this + " from JSON: " + jsonObject);
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

        for (CPlayer player:client.getGame().getPlayerList())
        //TODO check if rotation gets clockwise{
            if(clientID == player.getClientID()){
                if(rotation=="clockwise"){
                    player.getRobot().right();
                }else {
                    player.getRobot().left();
            }
        }
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD,new TaskContent()));

    }
}
