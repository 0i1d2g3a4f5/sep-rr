package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.game_elements.Checkpoint;
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

        switch (mapName){
            case "dizzy_highway"->{
                Checkpoint.numberOfCheckpoints = 1;
            }
            case "extra_crispy","lost_bearings","twister" -> {
                Checkpoint.numberOfCheckpoints = 4;
            }
            case "death_trap" ->{
                Checkpoint.numberOfCheckpoints = 5;
            }
            default -> {
                throw new IllegalArgumentException();
            }
            }

        return board;
    }

    public String readFile(String fileName){
        String filePath;

        switch (fileName){
            case "dizzy_highway"->{
                filePath = "dizzy_highway.json";
            }
            case "death_trap" ->{
                filePath = "death_trap.json";
            }
            case "extra_crispy" -> {
                filePath = "extra_crispy.json";
            }
            case "lost_bearings" -> {
                filePath = "lost_bearings.json";
            }
            case "twister" ->{
                filePath = "twister.json";
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
        return new JsonReader().readFile(filePath);
    }

    public static void main(String[] args) throws IOException {

    }



}
