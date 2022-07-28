package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.game_elements.Checkpoint;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Position;
import server_package.SClient;
import java.io.IOException;
import java.util.ArrayList;

import utility.SearchMethods;

public class MessageCheckPointMoved extends Message {
    /**
     * @author Mark Ringer
     */


    public int checkpointID;
    public int x;
    public int y;


    public MessageCheckPointMoved(int checkpointID,int y, int x){
        this.checkpointID = checkpointID;
        this.x = x;
        this.y = y;
        type = "CheckpointMoved";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("checkpointID", new JsonPrimitive(this.checkpointID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        //Server.serverLogger.info("Created Movement Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageCheckPointMoved(JsonObject jsonObject){
        super(jsonObject);
        checkpointID = content.get("checkpointID").getAsInt();
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

    /**@author Mark Ringer
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

        ArrayList<Checkpoint> checkpointList = Game.getInstance().getMap().getCheckpoints();

        Checkpoint checkpoint = SearchMethods.searchCheckpoint(checkpointID,checkpointList);
        Position eins = checkpoint.getGameField().getPosition();
        Position zwei = new Position(y, x);
        client.getHighSlumber().add(eins);
        client.getHighSlumber().add(zwei);
        if(checkpoint != null){
            checkpoint.moveCheckpointTo(y,x);
        }else {
            Client.clientLogger.error("Checkpoint not found");
        }

        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARDPARTS,new TaskContent()));
    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        /*
        if(checkpointID ==sentientClient.getId()){
            sentientClient.getPlayer().getRobot().moveRobotTo(y, x);
        }
        else{
            sentientClient.playerFromId(checkpointID).getPlayer().getRobot().moveRobotTo(y, x);
        }

         */
    }
}




