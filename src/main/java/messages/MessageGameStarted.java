package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.map.GameBoard;
import client_package.sentient.SentientClient;
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
        //Server.serverLogger.info("Created Game Started Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageGameStarted(JsonObject jsonObject) {
        super(jsonObject);
        gameMap = content.get("gameMap").getAsJsonArray();
        //Server.serverLogger.info("Created Game Started Message");
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
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        Client.clientLogger.info("Game Started");
        Game.getInstance().setMap(new GameBoard(content));

        Game game = Game.getInstance();
        game.getMap().constructLaserBeams();
        for (Client client2: client.getClientList()) {
            if(client2.getId() !=client.getId()){
                //TODO check if this needs to be done here or somewhere else
                //game.join(client2);
                //client_package.client_gamelogic.Game.getInstance().getPlayerList().add(new CPlayer(client2.getId(),client2.getRoboColor()));
            } else {

            }
        }
            client.setGame(game);

            client.getClientApplication().addAndExecuteTask(new Task(TaskType.TRIGGERSTART, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.triggerGameStart(content);
    }
}