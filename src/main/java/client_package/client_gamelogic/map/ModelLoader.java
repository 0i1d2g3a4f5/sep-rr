package client_package.client_gamelogic.map;

import com.google.gson.JsonObject;
import gamelogic.map.GameBoard;

import java.io.IOException;
/**
 * @author Ringer
 */
public class ModelLoader {

    /**
     * loads map
     *
     * @param mapName
     * @return
     * @author Mark Ringer, Vivian Kafadar
     */
    public client_package.client_gamelogic.map.GameBoard loadMap(String mapName) throws IOException {
        gamelogic.map.ModelLoader modelLoader = new gamelogic.map.ModelLoader();
        GameBoard helpBoard = modelLoader.loadMap(mapName);
        JsonObject hbtojson = helpBoard.toJson();
        return new client_package.client_gamelogic.map.GameBoard(hbtojson);
    }





}
