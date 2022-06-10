package gamelogic.map;



import com.google.gson.*;
import gamelogic.Position;
import gamelogic.game_elements.GameElement;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class GameBoard {
    int dimension1;
    int dimension2;
    public ArrayList<ArrayList<GameField>> boardMap;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String toString() {
        return "GameBoard{" +
                "boardMap=" + boardMap +
                '}';
    }

    /**
     * @author Ringer
     * adds the  GameElement parameter element to the boardMap
     * at the Position of the passed int parameters
     * @param y
     * @param x
     * @param element
     * @return
     */


    public boolean addElement(int y, int x,GameElement element){
        GameField field = boardMap.get(x).get(y);


        return field.addElement(element);
    }


    /**
     * @author Ringer
     * adds a Element
     * @param position
     * @param element
     * @return
     */
    public boolean addElement(Position position, GameElement element){
        GameField field = boardMap.get(position.getX()).get(position.getY());


        return field.addElement(element);
    }



    /**
     * returns a list of all GameElements of the map
     * @return
     */
    public ArrayList getRegistry(){
        /*
        ArrayList list = new ArrayList();
        boardMap.forEach((key,value)->{
            if(value.element!=null)
            list.add(value.element);
        });
        return list;

         */
        return null;
    }


/*
    /**
     * @author Ringer
     * @param leftTop
     * @param rightBottom
     * @param isActive
     */


    /**
     * @author Ringer
     * nitializes a map with the dimensions of the parameters
     * @param dimensionY
     * @param dimensionX
     */
    GameBoard(int dimensionY, int dimensionX){
        boardMap = new ArrayList<ArrayList<GameField>>();
        for(int x = 0;x<dimensionX;x++){
            boardMap.add(new ArrayList<GameField>());
            for (int y = 0;y<dimensionY;y++){
                boardMap.get(x).add(new GameField(y,x));
            }
        }

    }

    public GameField getGameField(int y, int x){
        return boardMap.get(x).get(y);

    }

    private static Pair<Integer,Integer> getDimensions(JsonArray arrayLVL1){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray arrayLVL2 = null;
        JsonArray arrayLVL3 = null;
        int x=0;
        int y=0;
        GameBoard board = new GameBoard(y,x);
        for (JsonElement elementLVL1:arrayLVL1) {
            arrayLVL2 = gson.fromJson(elementLVL1,JsonArray.class);
            for (JsonElement elementLVL2:arrayLVL2) {
                arrayLVL3 = gson.fromJson(elementLVL2,JsonArray.class);

                y++;
            }
            x++;
        }
        return new Pair<Integer,Integer>(y,x);

    }

    public static GameBoard fromJson(JsonObject json) throws IOException {

        Pair<Integer,Integer> dimensions = getDimensions((JsonArray) json.get("gameMap"));

        return buildFromJson(json,dimensions);
    }
    public static GameBoard fromJson(JsonObject json,Pair<Integer,Integer> dimensions) throws IOException {

        return buildFromJson(json,dimensions);
    }
    public static GameBoard buildFromJson(JsonObject json,Pair<Integer,Integer> dimensions) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray arrayLVL1 = (JsonArray) json.get("gameMap");
        JsonArray arrayLVL2 = null;
        JsonArray arrayLVL3 = null;
        GameBoard board = new GameBoard(dimensions.getKey(),dimensions.getValue());

        int x=0;
        int y=0;
        for (JsonElement elementLVL1:arrayLVL1) {
            arrayLVL2 = elementLVL1.getAsJsonArray();
            for (JsonElement elementLVL2:arrayLVL2) {
                GameField gameField = board.getGameField(y,x);
                arrayLVL3 = elementLVL2.getAsJsonArray();
                for (JsonElement elementLVL3:arrayLVL3) {
                    gameField.addElement(GameElement.fromJson(gson.fromJson(elementLVL3, JsonObject.class)));

                }

                y++;
            }
            x++;
        }
        return board;
    }

    /**
     * @author Ringer
     * returns a JsonArray of the nested lists. The struckture of the JsonArray represents the
     * structure of the ArrayLists
     * @return JsonArray
     */
    public JsonArray toJson(){
        JsonArray jsonArrayLVL1 = new JsonArray();
        for (ArrayList<GameField> listLVL1:boardMap) {
            JsonArray jsonArrayLVL2 = new JsonArray();
            for (GameField gameFieldLVL2:listLVL1) {
                JsonArray jsonArrayLVL3 = new JsonArray();

                for (GameElement element:gameFieldLVL2.getElements()) {
                    jsonArrayLVL3.add(element.toJson());
                }
                jsonArrayLVL2.add(jsonArrayLVL3);
            }
            jsonArrayLVL1.add(jsonArrayLVL2);
        }
        return jsonArrayLVL1;
    }






}
