package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;
import gamelogic.Game;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.MapName;

import java.io.IOException;
import java.io.Serializable;

import static gamelogic.map.MapName.DIZZY_HIGHWAY;

public class Antenna extends GameElement implements Serializable {


    public Antenna(Direction direction){
        super(ElementName.ANTENNA);
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
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        if(orientations.size()>0){
            Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
            this.orientations.add(direction);
            isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
        jsonObject.add("orientations",gson.toJsonTree(getOrientationsAsStrings()));
        return jsonObject;

    }
    /**
     * @author Ringer
     */
    public double calculateDistance(Robot robot){
        double sideA = robot.getPosition().getX()-this.getGameField().getPosition().getX();
        double sideB = robot.getPosition().getY()-this.getGameField().getPosition().getY();
        return Math.sqrt(Math.pow(sideA,2)+Math.pow(sideB,2));
    }

    @Override
    public String toString() {
        return "Antenna{" +
                "orientations=" + orientations +
                ", type=" + type +
                ", isOnBoard='" + isOnBoard + '\'' +
                '}';
    }
}
