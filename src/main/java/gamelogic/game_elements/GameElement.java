package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import gamelogic.Position;

import java.util.List;

public abstract class GameElement {

    public List<String> orientations;
    ElementName type = ElementName.NO_ELEMENT;

    public String isOnBoard;

    public Position position;


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameElement(){

    }

    public ElementName getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GameElement{" +
                "position=" + position +
                ", elementName=" + type +
                '}';
    }

    public GameElement(ElementName elementName ){
        this.type = elementName;

    }

    public JsonObject toJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;
    }


}
