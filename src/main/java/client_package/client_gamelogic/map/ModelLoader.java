package client_package.client_gamelogic.map;

import com.google.gson.JsonObject;
import gamelogic.map.GameBoard;

import java.io.IOException;
/**
 * @author Ringer
 */
public class ModelLoader {

    /**
     * @author Ringer
     * @param mapName
     * @return
     */
    public client_package.client_gamelogic.map.GameBoard loadMap(String mapName) throws IOException {
        gamelogic.map.ModelLoader helpMEEEE = new gamelogic.map.ModelLoader();
        GameBoard helpboard = helpMEEEE.loadMap(mapName);
        JsonObject hbtojson = helpboard.toJson();
        return new client_package.client_gamelogic.map.GameBoard(hbtojson);
    }


    public static void main(String[] args) throws IOException {
        ModelLoader loader = new ModelLoader();
        loader.loadMap("dizzy_highway");
    }



}
