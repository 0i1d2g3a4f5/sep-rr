package client_package.client_gamelogic.game_elements;

import client_package.Client;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.game_elements.robot.Robot;
import client_package.client_gamelogic.map.GameField;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Direction;



import server_package.Server;


import java.io.IOException;

public class Laser extends GameElement  {
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
        visualOrder = 3;
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
        visualOrder = 4;
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





    }

    /**
     * @author Mark Ringer
     *
     * adds LaserBeams to the gamefield
     *
     * @param gameField
     */
    public void laserMovement(GameField gameField) throws IOException {
        if(gameField != null){
            gameField.addElement(new LaserBeam(direction,count));

            GameField nextField = gameField.getNeighbor(direction);
            Client.clientLogger.debug("LaserMovement from "+gameField+ " to "+nextField+ " Direction: "+direction);
            if(nextField==null)return;
            if(nextField.getPosition().getY() <0 || nextField.getPosition().getY()>= Game.getInstance().getMap().getBoardMap().get(0).size() ||nextField.getPosition().getX() <0 || nextField.getPosition().getX()>= Game.getInstance().getMap().getBoardMap().size())
                return;
            else if(!gameField.checkWall(direction)&&!nextField.checkWall(direction.opposite()))
                laserMovement(nextField);
        }
    }
}
