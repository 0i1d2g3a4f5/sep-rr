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
        String resourceName = "/MapModels/"+fileName+".json";
        String filePath = getClass().getResource(resourceName).getFile();
        System.out.println("FILEPATH" + filePath);
        return new JsonReader().readFile(filePath);
    }

    public static void main(String[] args) throws IOException {

    }



}
