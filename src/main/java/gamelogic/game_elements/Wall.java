package gamelogic.game_elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.util.ArrayList;

public class Wall extends GameElement{
    public Wall(ArrayList<Direction> orientations){
        super(ElementName.WALL);
        for (Direction direction:orientations) {
            this.orientations.add(direction.toString());
        }

    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */

    @Override
    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;

    }
}
