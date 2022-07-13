package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.CPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import client_package.client_gamelogic.CPlayer;
import server_package.SClient;
import server_package.Server;

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
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @author Mark Ringer
     * places the Robot and updates the Map
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {



        if(client.getId()==clientID) {
            if(!client.getPlayer().getRobot().isPlaced()) {
                client.getPlayer().getRobot().placeRobot(y, x);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));
                //TODO disable place Robot Field
            }
        } else {
            System.out.println("setting other Robot");
            for (CPlayer player: client.getGame().getPlayerList()) {
                System.out.println("checking client:" + player.getClientID());
                if(player.getClientID() == clientID && clientID !=client.getId()){
                    System.out.println("LALALALALFGADLFASDKJLSGADKLSGDAKLGFDSALK");
                    System.out.println("figure: " +player.getRobot().getFigure());

                    player.getRobot().placeRobot(y,x);
                    client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));


                }

            }
        }

    }

}
