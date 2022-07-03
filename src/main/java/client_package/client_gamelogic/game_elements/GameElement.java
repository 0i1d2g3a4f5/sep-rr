package client_package.client_gamelogic.game_elements;

import client_package.client_gamelogic.map.GameField;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gamelogic.Direction;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GameElement {


    protected GameField gameField;
    public List<Direction> orientations = new ArrayList<Direction>();


    public ElementName getType(){
        return type;
    }

    protected ElementName type = ElementName.EMPTY;

    protected Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public GameElement(){

    }

    @Override
    public String toString() {
        return "GameElement{" +
                "orientations=" + orientations +
                ", type=" + type +
                ", gson=" + gson +
                '}';
    }


    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }


    public GameElement(ElementName elementName ){
        this.type = elementName;

    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameElement)) return false;
        GameElement element = (GameElement) o;
        return orientations.equals(element.orientations) && getType() == element.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientations, getType());
    }


}
