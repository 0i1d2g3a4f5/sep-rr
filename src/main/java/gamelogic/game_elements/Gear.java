package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import messages.MessagePlayerTurning;
import server_package.Server;

import java.io.IOException;

public class Gear extends GameElement implements Activatable {

    int activationOrder = 4;

    public GearDirection getGearDirection() {
        return gearDirection;
    }

    GearDirection gearDirection;

    /**
     * turns robot if on top
     * @author Mark Ringer
     */
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

        static GearDirection parseGearDirection(String s){
            for (GearDirection direction:GearDirection.values()
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
     */
    public Gear(GearDirection direction){
        Server.serverLogger.info("Gear direction: " + direction);
        gearDirection = direction;
        Server.serverLogger.info("Gear direction: " + direction);
        type = ElementName.GEAR;
        Server.serverLogger.info("Gear direction: " + direction);

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

        super(ElementName.GEAR);
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Server.serverLogger.info(orientations.get(0).getAsString());
        this.gearDirection = GearDirection.parseGearDirection(orientations.get(0).getAsString());

        isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
        JsonArray array = new JsonArray();
        array.add(gearDirection.toString());
        jsonObject.add("orientations",array);
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
