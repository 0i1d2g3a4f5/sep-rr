package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameField;

import java.io.IOException;

public class Laser extends GameElement implements Activatable {
    Direction direction;

    private int count;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Laser{" +
                "direction=" + direction +
                ", count=" + count +
                '}';
    }

    /**
     * @author Ringer
     * @param count
     * @param direction
     */
    public Laser(Direction direction, int count ) throws IOException {
        this.direction = direction;
        orientations.add(direction);
        if(count>3||count<1)
            throw new IOException("This Laser doesn't exist");
        this.count = count;
        type = ElementName.LASER;

    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Laser(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        Laser laser = new Laser(direction,jsonObject.get("count").getAsInt());
        laser.isOnBoard = jsonObject.get("isOnBoard").getAsString();


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
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }

    @Override
    public void activate() {
        laserMovement(gameField);
    }

    /**
     * @author Ringer
     * simulates the Lasermovement
     * @param gameField
     */

    private void laserMovement(GameField gameField){
        if(gameField != null){
            if(gameField.contains(ElementName.ROBOT)){
                Robot robot = (Robot) gameField.getElement(ElementName.ROBOT);
                robot.takeDamage(count);
            }else{
                GameField nextField = gameField.getNeighbor(direction);
                if(nextField !=null)
                    if(!gameField.checkWall(direction)&&!nextField.checkWall(direction.opposite())) laserMovement(nextField);
            }
        }
    }


    /**
     * @author Ringer
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT, GEAR, ROBOT, PUSHPANEL,ENERGYSPACE,EMPTY -> {
                return 1;
            }
            case CONVEYORBELT -> {
                ConveyorBelt cb = (ConveyorBelt) o;
                if(cb.getColor()== Color.BLUE) {
                    return -1;
                }else {
                    return 1;
                }
            }
            case LASER -> {
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
