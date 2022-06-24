package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Direction;

import java.io.IOException;
import java.util.ArrayList;

public class Gear extends GameElement implements Activatable {

    ArrayList<GearDirection> orientations;

    @Override
    public void activate() {

    }

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
        orientations.add(direction);
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

        GearDirection gearDirection;
        switch (orientations.get(0).getAsString()){
            case "clockwise" -> gearDirection = GearDirection.CLOCKWISE;
            case "counterclockwise" -> gearDirection = GearDirection.COUNTERCLOCKWISE;
            default -> throw new IOException("GearDirection not Found");
        }
        Gear gear = new Gear(gearDirection);
        gear.isOnBoard = jsonObject.get("isOnBoard").getAsString();




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

    /**
     * @author Ringer
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT-> {
                return 1;
            }
            case CONVEYORBELT, LASER,ROBOT,PUSHPANEL,ENERGYSPACE -> {
                return -1;
            }
            case GEAR -> {
                return 0;
            }
            default -> {
                try {
                    throw new IOException(o.getType()+" is Not Comparable");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
