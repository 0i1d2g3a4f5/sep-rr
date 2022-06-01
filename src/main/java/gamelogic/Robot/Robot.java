package gamelogic.Robot;

import gamelogic.Direction;
import gamelogic.Position;

/**
 * @author Qinyi
 * create a robot character for every player
 */
public class Robot {

    Direction directionFacing;
    private String name;

    private boolean isAlive;

    private Position nextPosition;
    private Direction nextDirectionFacing;

    /**
     * update the state and the location of the robot
     * @param location
     */

    /*
    public Robot (Location location){
        this.state = new RobotState(this, getMaxLives(), location, location);
    }


     */
    /**
     * @return the name of the robot
     */
    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }
    /**
     * @return the max Lives of a robot
     */
    public int getMaxLives(){
        return 3;
    }
/*
    public Location getLocation(){
        return this.state.getRobot().getLocation();
    }

 */


}
