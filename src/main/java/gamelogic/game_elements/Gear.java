package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class Gear extends GameElement{

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
        orientations.add(direction.toString());
        type = ElementName.GEAR;
    }
    boolean turnRight = false;

    public static Gear fromJson(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);

        GearDirection gearDirection;
        switch (orientations.get(0).getAsString()){
            case "clockwise" -> gearDirection = GearDirection.CLOCKWISE;
            case "counterclockwise" -> gearDirection = GearDirection.COUNTERCLOCKWISE;
            default -> throw new IOException("GearDirection not Found");
        }
        Gear gear = new Gear(gearDirection);
        gear.isOnBoard = jsonObject.get("isOnBoard").getAsString();



        return gear;
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
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;
    }
}
