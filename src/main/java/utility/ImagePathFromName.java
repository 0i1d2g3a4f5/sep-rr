package utility;
/**
 * @author Sarp Cagin Erdogan
 */
public enum ImagePathFromName {
    AGAIN ("Again.png"),
    BACK_UP ("Backup.png"),
    LEFT_TURN ("TurnLeft.png"),
    RIGHT_TURN ("TurnRight.png"),
    MOVE_ONE ("Move1.png"),
    MOVE_TWO ("Move2.png"),
    MOVE_THREE ("Move3.png"),
    POWER_UP ("PowerUp.png"),
    U_TURN ("UTurn.png");

    String path;
    private ImagePathFromName(String path){
        this.path=path;
    }
    public String toString(){
        return this.path;
    }

}
