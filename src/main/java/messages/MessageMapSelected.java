package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Game;
import gamelogic.map.GameBoard;
import gamelogic.map.MapName;
import gamelogic.map.ModelLoader;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageMapSelected extends Message{

    public String map;

    /**
     * converts message to json
     *
     * @param map
     * @author Isabel Muhm
     */
    public MessageMapSelected(String map) {
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageMapSelected(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
    }

    /**
     * sets map in gamelogic to selected map, starts game and informs clients about game start
     *
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @uthor Ringer
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        sClient.getServer().setGameStarted(true);
            ModelLoader modelLoader = new ModelLoader();
            GameBoard gameBoard = modelLoader.loadMap(map);
            try {
                Game.getInstance().setup(MapName.fromString(map));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Server.serverLogger.info("Received MAPSELECTEDMESSAGE. Map is : \n" + modelLoader.loadMap(map).toJson().toString());
            sClient.sendAll(this);
            sClient.getServer().setGameStarted(true);
            sClient.sendAll(new MessageGameStarted(gameBoard.toJson(), false));
            Game.getInstance().setStartPoints();
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {}
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
