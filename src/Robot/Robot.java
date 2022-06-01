package Robot;

/**
 * @author Qinyi
 * create a robot character for every player
 */
public class Robot {
    private String name;
    private RobotState state;

    /**
     * update the state and the location of the robot
     * @param location
     */
    public Robot (Location location){
        this.state = new RobotState(this, getMaxLives(), location, location);
    }

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

    public Location getLocation(){
        return this.state.getRobot().getLocation();
    }


}
