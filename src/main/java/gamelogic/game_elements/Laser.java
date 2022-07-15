package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.Game;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameField;
import server_package.Server;

import java.io.IOException;

public class Laser extends GameElement implements Activatable {

    int activationOrder = 5;
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
        super(ElementName.LASER);
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
        super(ElementName.LASER);
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        if(orientations.size()>0){
            Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
            this.direction = direction;
            this.orientations.add(direction);
        }

        /*if(count>3||count<1)
            throw new IOException("This Laser doesn't exist");

         */
        this.count = jsonObject.get("count").getAsInt();
        isOnBoard = jsonObject.get("isOnBoard").getAsString();


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
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }

    @Override
    public void activate() {
        Server.serverLogger.info("Activate " + type);
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
                Server.serverLogger.info("Laser hits Robot "+gameField.getRobot());
                Robot robot = (Robot) gameField.getElement(ElementName.ROBOT);
                robot.takeDamage(count);
            }else{
                GameField nextField = gameField.getNeighbor(direction);
                if(nextField.getPosition().getY() <0 || nextField.getPosition().getY()>= Game.getInstance().board.getBoardMap().get(0).size() ||nextField.getPosition().getX() <0 || nextField.getPosition().getX()>= Game.getInstance().board.getBoardMap().size())
                    return;
                else if(!gameField.checkWall(direction)&&!nextField.checkWall(direction.opposite())) laserMovement(nextField);
            }
        }
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }


    /**
     * @author Ringer
     *
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
