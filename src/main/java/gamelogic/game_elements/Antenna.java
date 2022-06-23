package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;
import gamelogic.Game;
import gamelogic.game_elements.robot.Robot;

import java.io.IOException;
import java.io.Serializable;

public class Antenna extends GameElement implements Serializable {
    public Antenna(Direction direction){
        type = ElementName.ANTENNA;
        orientations.add(direction.toString());
        Game.getInstance().board.setAntenna(this);
    }


    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Antenna(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        Antenna antenna = new Antenna(direction);
        antenna.isOnBoard = jsonObject.get("isOnBoard").getAsString();
        Game.getInstance().board.setAntenna(this);
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