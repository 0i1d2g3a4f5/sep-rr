package gamelogic.map;



import com.google.gson.*;
import gamelogic.Activatable;
import gamelogic.JsonSerializable;
import gamelogic.Position;
import gamelogic.game_elements.*;
import javafx.util.Pair;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GameBoard implements JsonSerializable {
    protected int dimensionY;
    protected int dimensionX;
    private Antenna antenna;

    private ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
    public ArrayList<ArrayList<GameField>> boardMap;
    public ArrayList<ArrayList<GameField>> getBoardMap() {
        return boardMap;
    }

    public ArrayList<GameField> getColumn(int column){
        return boardMap.get(column);
    }

    public GameField getField(int y, int x){

        if(y <0 || y>= boardMap.get(0).size() ||x <0 || x>= boardMap.size()){
            System.out.println("illegal Field");
            Server.serverLogger.info("Illegal field");

            GameField gameField = new GameField(this,y,x);
            gameField.addElement(new Pit());
            return gameField;


        }


        return boardMap.get(x).get(y);
    }
    public GameField getField(Position position){

        if(position == null){
            System.out.println("illegal Field");
            Server.serverLogger.info("Illegal field");

            GameField gameField = new GameField(this,position.getY(),position.getX());
            gameField.addElement(new Pit());
            return gameField;
        }


        if(position.getY() <0 || position.getY()>= boardMap.get(0).size()||position.getX() <0 || position.getX()>= boardMap.size()){

            System.out.println("illegal Field");
            Server.serverLogger.info("Illegal field");

            GameField gameField = new GameField(this,position.getY(),position.getX());
            gameField.addElement(new Pit());
            return gameField;


        }


        return boardMap.get(position.getX()).get(position.getY());
    }

    public ArrayList<RestartPoint> restartPoints = new ArrayList<>();



    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public int getDimensionX(){
        return dimensionX;
    }
    public int getDimensionY(){
        return dimensionY;
    }

    public void addCheckpoint(Checkpoint checkpoint){
        checkpoints.add(checkpoint);
    }
    public ArrayList<Checkpoint> getCheckpoints(){
        return checkpoints;
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

        element.setGameField(field);
        element.setPosition(y,x);

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
        return addElement(position.getY(), position.getX(), element);
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
                boardMap.get(x).add(new GameField(this, y, x));
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

    public GameBoard(JsonObject json) throws IOException {
        //TODO ?

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ElementFactory elementFactory = new ElementFactory();
        boardMap = new ArrayList<ArrayList<GameField>>();


        JsonArray arrayLVL1 = (JsonArray) json.get("gameMap");
        JsonArray arrayLVL2;
        JsonArray arrayLVL3;

        int x=0;
        int y=0;
        for (int i=0; i<arrayLVL1.getAsJsonArray().size();i++) {
            ArrayList<GameField> row = new ArrayList<GameField>();
            for (int j=0; j<arrayLVL1.get(i).getAsJsonArray().size(); j++) {
                //System.out.println("Y :: " + j + " :: " + arrayLVL1.get(i).getAsJsonArray().get(j).getAsJsonArray().toString());
                GameField gameField = new GameField(this,j,i);

                for (JsonElement elementLVL3: arrayLVL1.get(i).getAsJsonArray().get(j).getAsJsonArray()) {
                    GameElement element = elementFactory.createElement(gson.fromJson(elementLVL3, JsonObject.class));
                    element.setGameField(gameField);
                    gameField.isOnBoard=element.getIsOnBoard();

                    //System.out.println("Type after Factory: "+element.getType());
                    gameField.addElement(element);

                    if(element.getType() == ElementName.RESTARTPOINT){
                        restartPoints.add((RestartPoint) element);
                    }
                    if(element.getType() == ElementName.ANTENNA){
                        setAntenna((Antenna) element);
                    }
                    if(element instanceof Checkpoint){
                        addCheckpoint((Checkpoint) element);
                    }
                }
                row.add(gameField);
            }
            boardMap.add(row);
        }
        //System.out.println(toJson());

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
                        //System.out.println("Element in Board.toJson: "+element);
                        //Server.serverLogger.info("Element in Board.toJson");
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
