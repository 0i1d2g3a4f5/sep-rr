package client_package.client_gamelogic.map;



import client_package.client_gamelogic.game_elements.*;
import com.google.gson.*;


import gamelogic.Position;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameBoard  {
    protected int dimensionY;
    protected int dimensionX;

    public ArrayList<ArrayList<GameField>> boardMap;
    public ArrayList<ArrayList<GameField>> getBoardMap() {
        return boardMap;
    }


    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
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

    public JsonObject toJson(){
        JsonObject jsonMap = new JsonObject();

        JsonArray jsonArrayLVL1 = new JsonArray();
        for (ArrayList<GameField> listLVL1:boardMap) {
            JsonArray jsonArrayLVL2 = new JsonArray();
            for (GameField gameField:listLVL1) {
                JsonArray jsonArrayLVL3 = new JsonArray();
                if(gameField.isActive()){
                    for (GameElement element:gameField.getElements()) {
                        //System.out.println("Element in Board.toJson: "+element);
                        jsonArrayLVL3.add(new JsonPrimitive(element.getType().toString()));
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


    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public int getDimensionX(){
        return dimensionX;
    }
    public int getDimensionY(){
        return dimensionY;
    }



    @Override
    public String toString() {
        return "GameBoard{" +
                "dimensionY=" + dimensionY +
                ", dimensionX=" + dimensionX +
                ", boardMap=" + boardMap +
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
     * @author Ringer
     * nitializes a map with the dimensions of the parameters
     * @param dimensionY
     * @param dimensionX
     */
    public GameBoard(int dimensionY, int dimensionX){
        this.dimensionY = dimensionY;
        this.dimensionX = dimensionX;
        boardMap = new ArrayList<ArrayList<GameField>>();
        for(int x = 0;x<dimensionX;x++){
            boardMap.add(new ArrayList<GameField>());
            for (int y = 0;y<dimensionY;y++){
                boardMap.get(x).add(new GameField(this, x, y));
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
    /**
     * @author Ringer
     * overloaded Constructor
     * builds the game board from the JsonObject
     * @param json
     * @return
     * @throws IOException
     */

    public GameBoard (JsonObject json) throws IOException {
        //TODO ?

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ElementFactory elementFactory = new ElementFactory();
        boardMap = new ArrayList<ArrayList<GameField>>();


        JsonArray arrayLVL1 = (JsonArray) json.get("gameMap");
        JsonArray arrayLVL2;
        JsonArray arrayLVL3;
        this.setDimensionX(arrayLVL1.getAsJsonArray().size());
        this.setDimensionY(arrayLVL1.get(0).getAsJsonArray().size());

        int x=0;
        int y=0;
        for (int i=0; i<arrayLVL1.getAsJsonArray().size();i++) {
            ArrayList<GameField> row = new ArrayList<GameField>();
            for (int j=0; j<arrayLVL1.get(i).getAsJsonArray().size(); j++) {
                GameField gameField = new GameField(this,j,i);

                for (JsonElement elementLVL3: arrayLVL1.get(i).getAsJsonArray().get(j).getAsJsonArray()) {
                    GameElement element = elementFactory.createElement(gson.fromJson(elementLVL3, JsonObject.class));
                    //System.out.println("Type after Factory: "+element.getType());
                    gameField.addElement(element);

                }
                row.add(gameField);
            }
            boardMap.add(row);
        }
        dimensionX = x;
        x=0;
        //System.out.println(boardMap);

    }

    public ArrayList<GameElement> getElements(int y, int x){
        GameField gameField = getGameField(y,x);
        return gameField.getElements();
    }


    /**
     * @author Ringer
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard gameBoard = (GameBoard) o;
        return dimensionY == gameBoard.dimensionY && dimensionX == gameBoard.dimensionX && boardMap.equals(gameBoard.boardMap);
    }

    public int createDimensionX(){
        setDimensionX(boardMap.size());
        return boardMap.size();
    }
    public int createDimensionY(){
        setDimensionY(boardMap.get(1).size());
        return boardMap.get(1).size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimensionY, dimensionX, boardMap);
    }






}
