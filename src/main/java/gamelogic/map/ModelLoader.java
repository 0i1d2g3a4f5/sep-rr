package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

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
        mapJson =  gson.fromJson(jsonString,JsonObject.class);

        board =GameBoard.fromJson(mapJson);
        return board;
    }

    public String readFile(String fileName){

        String filePath = "src/main/resources/MapOfJson/"+fileName+".json";
        File file= new File(filePath);
        BufferedReader reader;
        try {
            reader= new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String jsonString="";
        String input;
        while (true) {
            try {
                if (!((input=reader.readLine())!=null)) break;
                jsonString+=input;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonString;
    }



}
