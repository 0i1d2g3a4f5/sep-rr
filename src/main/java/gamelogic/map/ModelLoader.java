package gamelogic.map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class ModelLoader {

    /**
     * @author Ringer
     * @param mapName
     * @return
     */
    public GameBoard loadMap(String mapName) throws IOException {
        GameBoard board;
        Gson gson = new Gson();
        String filePath = mapName;
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
        JsonObject mapJson =  JsonParser.parseString(jsonString).getAsJsonObject();



        MapDeserializer deserializer = new MapDeserializer();

        board =deserializer.deserializeMap(mapJson);
        return board;
    }



}
