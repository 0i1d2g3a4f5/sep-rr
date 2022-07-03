package client_package.client_gamelogic.game_elements.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.IOException;

public class Gear extends GameElement{


    public GearDirection getGearDirection() {
        return gearDirection;
    }

    GearDirection gearDirection;

    public enum GearDirection{
        CLOCKWISE("clockwise"),
        COUNTERCLOCKWISE("counterclockwise");

        String direction;

        public String toString(){
            return direction;
        }

        GearDirection(String direction) {
            this.direction = direction;
        }
    }
    public Gear(GearDirection direction){
        gearDirection = direction;
        type = ElementName.GEAR;
    }
    boolean turnRight = false;

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Gear (JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);

        GearDirection gearDirection = GearDirection.valueOf(orientations.get(0).getAsString());
        Gear gear = new Gear(gearDirection);

    }

}
