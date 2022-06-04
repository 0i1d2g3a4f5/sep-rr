package gamelogic.map;

import com.google.gson.Gson;

import java.io.*;

public class ModelLoader {

    /**
     * @author Ringer
     * @param mapName
     * @return
     */
    public GameBoard loadMap(String mapName){
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


        String json="";
        String input;
        while (true) {
            try {
                if (!((input=reader.readLine())!=null)) break;
                json+=input;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        MapSerializer serializer = new MapSerializer();
        board =serializer.deserializeBoard(json);
        return board;
    }



}
