package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Direction;

import java.io.IOException;
import java.io.Serializable;
/**
 * @author Mark Ringer
 */

public class Antenna extends GameElement implements Serializable {


    public Antenna(Direction direction){
        visualOrder = 1;
        type = ElementName.ANTENNA;
        orientations.add(direction);

    }


    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Antenna(JsonObject jsonObject) throws IOException {
        super(ElementName.ANTENNA);
        visualOrder = 1;
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        if(orientations.size()>0){
            Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
            this.orientations.add(direction);

        }



    }


    @Override
    public String toString() {
        return "Antenna{" +
                "orientations=" + orientations +
                ", type=" + type +
                '}';
    }
}
