package client_package.client_gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utility.JsonReader;

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
    public GameBoard loadMap(String mapName) throws IOException {

        System.out.println("rdy");
        gamelogic.map.ModelLoader helpMEEEE = new gamelogic.map.ModelLoader();
        System.out.println("creadted loader ");
        gamelogic.map.GameBoard helpboard = helpMEEEE.loadMap(mapName);
        System.out.println("loaded map");
        JsonObject obhqawfbaeqfg = helpboard.toJson();
        System.out.println("tojson");

        return new GameBoard(obhqawfbaeqfg);
    }

    public String readFile(String fileName){

        String filePath = "src/main/resources/MapModels/"+fileName+".json";
        return new JsonReader().readFile(filePath);
    }

    public static void main(String[] args) throws IOException {
        ModelLoader loader = new ModelLoader();
        loader.loadMap("dizzy_highway");
    }



}
