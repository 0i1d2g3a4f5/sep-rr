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



public class MessageMovement extends Message{

    public int clientID;
    public int x;
    public int y;

    /**
     * @param clientID
     * @param x
     * @param y
     */
    public MessageMovement(int clientID, int x, int y){
        this.clientID = clientID;
        this.x = x;
        this.y = y;
        type = "Movement";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        System.out.println("Created Move Message: " + this);
        Server.serverLogger.info("Created Movement Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageMovement(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        x= content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        System.out.println("Created Move Message: " + this + " from JSON: " + jsonObject);
        Server.serverLogger.info("Created Movement Message: " + this + " from JSON: " + jsonObject);
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

    /**@author Mark Ringer
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        for (CPlayer player:client.getGame().getPlayerList()) {
            if(clientID == player.getClientID()){
                player.getRobot().moveRobotTo(y,x);
            }else {
                for (CPlayer cplayer:client.getGame().getPlayerList()) {
                    cplayer.getRobot().moveRobotTo(y,x);
                }
            }
        }
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD,new TaskContent()));
    }
}
