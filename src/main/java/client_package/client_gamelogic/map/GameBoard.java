package client_package.client_gamelogic.map;



import client_package.client_gamelogic.game_elements.ElementFactory;
import client_package.client_gamelogic.game_elements.GameElement;
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

    public ArrayList<GameField> getColumn(int column){
        return boardMap.get(column);
    }

    public GameField getField(int y, int x){
        return boardMap.get(x).get(y);
    }
    public GameField getField(Position position){
        return boardMap.get(position.getX()).get(position.getY());
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
        boardMap = boardMap = new ArrayList<ArrayList<GameField>>();
        ArrayList<GameField> row = new ArrayList<GameField>();

        JsonArray arrayLVL1 = (JsonArray) json.get("gameMap");
        JsonArray arrayLVL2;
        JsonArray arrayLVL3;

        int x=0;
        int y=0;
        for (JsonElement elementLVL1:arrayLVL1) {
            arrayLVL2 = elementLVL1.getAsJsonArray();
            for (JsonElement elementLVL2:arrayLVL2) {
                GameField gameField = new GameField(this,y,x);
                arrayLVL3 = elementLVL2.getAsJsonArray();
                for (JsonElement elementLVL3:arrayLVL3) {
                    GameElement element = elementFactory.createElement(gson.fromJson(elementLVL3, JsonObject.class));
                    gameField.addElement(element);
                }
                row.add(gameField);
                y++;
            }
            boardMap.add(row);
            y=0;
            x++;
        }
        System.out.println(boardMap);

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

    @Override
    public int hashCode() {
        return Objects.hash(dimensionY, dimensionX, boardMap);
    }






}
