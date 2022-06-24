package gamelogic.game_elements;

import com.google.gson.*;
import gamelogic.Direction;

import java.io.IOException;
import java.util.ArrayList;

public class Wall extends GameElement{
    public Wall(ArrayList<Direction> orientations){
        super(ElementName.WALL);
        for (Direction direction:orientations) {
            this.orientations.add(direction);
        }

    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Wall(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        ArrayList<Direction> directions = new ArrayList<>();
        for (JsonElement orientation:orientations) {
            directions.add(Direction.parseDirection(orientation.getAsString()));
        }

        Wall wall = new Wall(directions);
        wall.isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
