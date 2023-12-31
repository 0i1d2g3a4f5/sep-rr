package client_package.client_gamelogic.game_elements;

import com.google.gson.*;
import gamelogic.Direction;

import java.io.IOException;
import java.util.ArrayList;

public class Wall extends GameElement {
    public Wall(ArrayList<Direction> orientations){
        super(ElementName.WALL);
        visualOrder = 5;
        for (Direction direction:orientations) {
            this.orientations.add(direction);
        }

    }

    /**
     * builds an Object from a JsonObject
     *
     * @author Ringer
     *
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Wall(JsonObject jsonObject) throws IOException {
        super(ElementName.WALL);
        visualOrder = 5;
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);

        for (JsonElement orientation:orientations) {
            this.orientations.add(Direction.parseDirection(orientation.getAsString()));
        }


    }

}
