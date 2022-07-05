package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Game;
import gamelogic.map.GameBoard;
import gamelogic.map.ModelLoader;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageMapSelected extends Message{

    public String map;

    /**
     * @param map
     */
    public MessageMapSelected(String map) {
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageMapSelected(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     * @uthor Ringer
     */

    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            //Game.getInstance().setMapName(MapName.valueOf(map));
            ModelLoader modelLoader = new ModelLoader();
            GameBoard gameBoard = modelLoader.loadMap(map);
            Game.getInstance().setBoard(gameBoard);
            System.out.println("Received MAPSELECTEDMESSAGE. Map is : \n" + modelLoader.loadMap(map).toJson().toString());
            sClient.sendAll(this);
            //TODO select position, revert to get gameBoard
            sClient.sendAll(new MessageGameStarted(gameBoard.toJson(), false));

        }
        else {
            //ADVANCED
        }
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        client_package.client_gamelogic.map.ModelLoader modelLoader = new client_package.client_gamelogic.map.ModelLoader();
        client.getGame().setMap(modelLoader.loadMap(map));


    }
}
