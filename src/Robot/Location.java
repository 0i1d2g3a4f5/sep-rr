package Robot;

/**
 * @author Qinyi
 * set the init Location of the src.Robot
 * the Location will change according to the programming cards
 */
public class Location {
    private Position position;
    private Direction direction;
    public Location(Position position, Direction direction){
        this.position = position;
        this.direction = direction;
    }

    public Location(){
        this(new Position(0,0),Direction.EAST);
    }

    public Direction getDirection(){
        return this.direction;
    }

    public Position getPosition(){
        return this.position;
    }

}

