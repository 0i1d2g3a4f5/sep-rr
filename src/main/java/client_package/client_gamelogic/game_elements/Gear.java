package client_package.client_gamelogic.game_elements;

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
        static GearDirection parseGearDirection(String s){
            for (GearDirection direction: GearDirection.values()
            ) {
                if(direction.toString().equalsIgnoreCase(s)){
                    return direction;
                }
            }
            throw new IllegalArgumentException();
        }

        GearDirection(String direction) {
            this.direction = direction;
        }
    }

    /**
     * Constructor
     * @param direction
     * @author Mark Ringer
     */
    public Gear(GearDirection direction){
        visualOrder = 1;
        gearDirection = direction;
        type = ElementName.GEAR;
    }
    boolean turnRight = false;

    /**
     * builds an Object from a JsonObject
     *
     * @author Ringer
     *
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Gear (JsonObject jsonObject) throws IOException {
        super(ElementName.GEAR);
        visualOrder = 1;
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);

        GearDirection gearDirection = GearDirection.parseGearDirection(orientations.get(0).getAsString());
        this.gearDirection = gearDirection;

    }

}
