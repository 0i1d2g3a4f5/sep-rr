package gamelogic.map;



import com.google.gson.*;
import gamelogic.Activatable;
import gamelogic.JsonSerializable;
import gamelogic.Position;
import gamelogic.game_elements.Antenna;
import gamelogic.game_elements.ElementFactory;
import gamelogic.game_elements.GameElement;
import gamelogic.game_elements.robot.Robot;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GameBoard implements JsonSerializable {
    protected int dimensionY;
    protected int dimensionX;



    private Antenna antenna;
    public ArrayList<ArrayList<GameField>> getBoardMap() {
        return boardMap;
    }

    public ArrayList<GameField> getColumn(int column){
        return boardMap.get(column);
    }

    public GameField getField(int y, int x){
        return boardMap.get(x).get(y);
    }
    public GameField getField(Position position){
        return boardMap.get(position.getX()).get(position.getY());
    }

    public ArrayList<ArrayList<GameField>> boardMap;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();



    @Override
    public String toString() {
        return "GameBoard{" +
                "dimensionY=" + dimensionY +
                ", dimensionX=" + dimensionX +
                ", boardMap=" + boardMap +
                ", gson=" + gson +
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

    public Antenna getAntenna() {
        return antenna;
    }

    public void setAntenna(Antenna antenna) {
        this.antenna = antenna;
    }

    /**
     * @author Ringer
     * returns a sorted list of all GameElements of the map
     *
     * @return ArrayList<Activatable></>
     */
    public ArrayList<Activatable> getRegistry(){

        ArrayList list = new ArrayList();
        for (ArrayList<GameField> fieldList:boardMap) {
            for (GameField field:fieldList) {
                for (GameElement element: field.getElements()) {
                    if((element instanceof Activatable) )
                        list.add(element);
                }
            }
        }
        Collections.sort(list);
        return list;

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
        this.dimensionY = dimensionY;
        this.dimensionX = dimensionX;
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

    /**
     * Method is used to receive the dimensions of the Map if they are not available
     * @param arrayLVL1
     * @return
     */
    private static Pair<Integer,Integer> getDimensions(JsonArray arrayLVL1){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray arrayLVL2 = null;
        JsonArray arrayLVL3 = null;
        int x=arrayLVL1.size();
        int y=0;

        for (JsonElement elementLVL1:arrayLVL1) {
            arrayLVL2 = gson.fromJson(elementLVL1,JsonArray.class);
            if(arrayLVL2.size()>y) y=arrayLVL2.size();
        }
        return new Pair<Integer,Integer>(y,x);

    }

    /**
     * @author Ringer
     * overloaded Constructor
     * @param json
     * @return
     * @throws IOException
     */
    public GameBoard (JsonObject json) throws IOException {

        Pair<Integer,Integer> dimensions = getDimensions((JsonArray) json.get("gameMap"));

        new GameBoard(json,dimensions);
    }

    /**
     * @author Ringer
     * overloaded Constructor
     * builds the game board from the JsonObject
     * @param json
     * @param dimensions
     * @return
     * @throws IOException
     */

    public GameBoard (JsonObject json,Pair<Integer,Integer> dimensions) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ElementFactory elementFactory = new ElementFactory();

        JsonArray arrayLVL1 = (JsonArray) json.get("gameMap");
        JsonArray arrayLVL2;
        JsonArray arrayLVL3;


        int x=0;
        int y=0;
        for (JsonElement elementLVL1:arrayLVL1) {
            arrayLVL2 = elementLVL1.getAsJsonArray();
            for (JsonElement elementLVL2:arrayLVL2) {
                GameField gameField = this.getGameField(y,x);
                arrayLVL3 = elementLVL2.getAsJsonArray();
                for (JsonElement elementLVL3:arrayLVL3) {
                    gameField.addElement(elementFactory.createElement(gson.fromJson(elementLVL3, JsonObject.class)));

                }

                y++;
            }
            y=0;
            x++;
        }

    }



    /**
     * @author Ringer
     * returns a JsonObject with an JsonArray of the nested lists. The structure of the JsonArray represents the
     * structure of the ArrayLists
     * @return JsonArray
     */
    public JsonObject toJson(){
        JsonObject jsonMap = new JsonObject();

        JsonArray jsonArrayLVL1 = new JsonArray();
        for (ArrayList<GameField> listLVL1:boardMap) {
            JsonArray jsonArrayLVL2 = new JsonArray();
            for (GameField gameField:listLVL1) {
                JsonArray jsonArrayLVL3 = new JsonArray();
                if(gameField.isActive()){
                    for (GameElement element:gameField.getElements()) {
                        jsonArrayLVL3.add(element.toJson());
                    }
                } else {
                    jsonArrayLVL3.add(gson.toJson(null));
                }

                jsonArrayLVL2.add(jsonArrayLVL3);
            }
            jsonArrayLVL1.add(jsonArrayLVL2);
        }
        jsonMap.add("gameMap",jsonArrayLVL1);
        return jsonMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard gameBoard = (GameBoard) o;
        return dimensionY == gameBoard.dimensionY && dimensionX == gameBoard.dimensionX && boardMap.equals(gameBoard.boardMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimensionY, dimensionX, boardMap);
    }






}
