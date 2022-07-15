package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utility.JsonReader;

import java.io.*;
/**
 * @author Ringer
 */
public class ModelLoader {

    Gson gson = new Gson();
    String jsonString;
    JsonObject mapJson;
    /**
     * @author Ringer
     * @param mapName
     * @return
     */
    public GameBoard loadMap(String mapName) throws IOException {

        GameBoard board;
        jsonString= readFile(mapName);
        mapJson = gson.fromJson(jsonString,JsonObject.class);

        board =new GameBoard(mapJson);
        return board;
    }

    public String readFile(String fileName){

        String filePath = "src/main/resources/MapModels/"+fileName+".json";
        return new JsonReader().readFile(filePath);
    }

    public static void main(String[] args) throws IOException {

    }



}
