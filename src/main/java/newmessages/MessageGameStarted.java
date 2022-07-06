package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.ClientObject;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.Player;
import client_package.client_gamelogic.ThisPlayer;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageGameStarted extends Message{
    public JsonArray gameMap;
    public MessageGameStarted(JsonObject jsonObject, boolean a){
        type = "GameStarted";
        content = jsonObject;
        System.out.println("GameStarted content: "+content);
    }

    /**
     * @param jsonObject
     */
    public MessageGameStarted(JsonObject jsonObject) {
        super(jsonObject);
        gameMap = content.get("gameMap").getAsJsonArray();
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
        for (ClientObject client2: client.getClientList()) {
            if(client2.getId() !=client.getId()){
                //TODO check if this needs to be done here or somewhere else
                //client_package.client_gamelogic.Game.getInstance().getPlayerList().add(new Player(client2.getId(),client2.getRoboColor()));
            } else {
                client.setPlayer(new ThisPlayer(client,client.getGame()));
                client.getGame().getPlayerList().add(client.getPlayer());
            }


        }
        if(isBasic) {
            client.getGame().setMap(new GameBoard(content));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.TRIGGERSTART, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));
        }
        else {
            //ADVANCED
        }
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException{

    }
}