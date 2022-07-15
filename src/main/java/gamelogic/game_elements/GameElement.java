package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.JsonSerializable;
import gamelogic.Position;
import gamelogic.map.GameField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GameElement implements JsonSerializable {


    protected Position position;
    protected GameField gameField;
    public List<Direction> orientations = new ArrayList<Direction>();


    public ElementName getType(){
        return type;
    }

    protected ElementName type;



    protected String isOnBoard;

    protected Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public GameElement(){

    }


    public String getIsOnBoard() {
        return isOnBoard;
    }
    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }


    public GameElement(ElementName elementName){
        this.type = elementName;
        this.isOnBoard = isOnBoard;

    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */

    public abstract JsonObject toJson();

    public ArrayList<String> getOrientationsAsStrings(){
        ArrayList<String> outputList = new ArrayList<>();

        for (Direction direction:orientations) {
            outputList.add(direction.toString());
        }

        return outputList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameElement)) return false;
        GameElement element = (GameElement) o;
        return orientations.equals(element.orientations) && getType() == element.getType() && isOnBoard.equals(element.isOnBoard);
    }

    @Override
    public String toString() {
        return "GameElement:=§(" +
                "orientations=" + orientations +"|"+
                ", type=" + type +"|"+
                ", isOnBoard='" + isOnBoard +
                ")§";
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientations, getType(), isOnBoard);
    }


    public void setPosition(int y, int x) {
        this.position = new Position(y,x);
    }
}
