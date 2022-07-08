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
       //System.out.println("in ModelLoaderbefore parsing: "+mapJson);

        board =new GameBoard(mapJson);
        //System.out.println("Map in loader: "+board.toJson());
        return board;
    }

    public String readFile(String fileName){

        String filePath = "src/main/resources/MapModels/"+fileName+".json";
        return new JsonReader().readFile(filePath);
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(new ModelLoader().loadMap("dizzy_highway"));
    }



}
