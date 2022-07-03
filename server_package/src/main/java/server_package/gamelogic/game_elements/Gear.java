package server_package.gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.gamelogic.Activatable;
import server_package.newmessages.MessagePlayerTurning;

import java.io.IOException;

public class Gear extends GameElement implements Activatable {

    int activationOrder = 4;

    public GearDirection getGearDirection() {
        return gearDirection;
    }

    GearDirection gearDirection;

    @Override
    public void activate() {
        if(gameField.contains(ElementName.ROBOT)){
            if(gearDirection==GearDirection.CLOCKWISE){
                gameField.getRobot().right();
                gameField.getRobot().getPlayer().getClient().sendAll(new MessagePlayerTurning(gameField.getRobot().getPlayer().getClient().getId(),"clockwise"));
            } else{
                gameField.getRobot().left();
                gameField.getRobot().getPlayer().getClient().sendAll(new MessagePlayerTurning(gameField.getRobot().getPlayer().getClient().getId(),"counterclockwise"));
            }
        }


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
        jsonObject.add("orientations",gson.toJsonTree(gearDirection));
        return jsonObject;
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    /**
     * @author Ringer
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }
}