package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class GameElement {

    public List<String> orientations = new ArrayList<String>();

    public ElementName getType(){
        return type;
    }

    ElementName type = ElementName.EMPTY;

    public String isOnBoard = "B1";

    public Position position;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameElement(){


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

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */

    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));
        //jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;
    }


}
