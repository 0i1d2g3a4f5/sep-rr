package Robot;

/** set the state of the Robots
 * @author Qinyi
 */
public class RobotState {
    private Robot robot;
    private int Lives;
    private Location location;
    private Location updateLocation;
    public RobotState(Robot robot, int Lives, Location location, Location updateLocation) {
        this.robot = robot;
        this.Lives = Lives;
        this.location = location;
        this.updateLocation = updateLocation;
    }

    public Robot getRobot(){
        return robot;
    }

}
