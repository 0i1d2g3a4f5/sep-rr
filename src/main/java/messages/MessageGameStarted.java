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
     * creates task to start the game (and game scene) and to update the gameboard
     *
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
        client.setGame(game);
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.TRIGGERSTART, new TaskContent()));
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARD, new TaskContent()));

    }

    /**
     * triggers game start of AI
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @auhor Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.getSentientBehaviour().triggerGameStart(content);
    }
}