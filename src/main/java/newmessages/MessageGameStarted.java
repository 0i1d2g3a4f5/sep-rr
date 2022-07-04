package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskString1;
import client_application.TaskType;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.Player;
import client_package.client_gamelogic.ThisPlayer;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageGameStarted extends Message{
    public JsonObject gameMap;
    public MessageGameStarted(JsonObject jsonObject, boolean a){
        type = "GameStarted";
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageGameStarted(JsonObject jsonObject) {
        super(jsonObject);
        gameMap = content.get("gameMap").getAsJsonObject();
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        for (client_package.Client client2: client.getClientList()) {
            if(client2.getId() !=client.getId()){
                client_package.client_gamelogic.Game.getInstance().getPlayerList().add(new Player(client2.getId(),client2.getRoboColor()));
            } else {
                client.setPlayer(new ThisPlayer(client2.getId(),client2.getRoboColor()));
                client_package.client_gamelogic.Game.getInstance().getPlayerList().add(client.getPlayer());
            }


        }
        if(isBasic) {
            Game.getInstance().setMap(new GameBoard(gameMap));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.TRIGGERSTART, new TaskContent()));
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