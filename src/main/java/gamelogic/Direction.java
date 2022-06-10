package gamelogic;

import java.io.IOException;

/**
 * @author Qinyi
 * to make the direction of the robot's approaching clear
 */
public enum Direction{
    NORTH("top"){
        public Direction left(){
            return WEST;
        }

        public Direction right(){
            return EAST;
        }

        public Direction opposite(){
            return SOUTH;
        }
    },

    SOUTH("bottom"){
        public Direction left(){
            return EAST;
        }

        public Direction right(){
            return WEST;
        }

        public Direction opposite(){
            return NORTH;
        }
    },

    EAST("right"){
        public Direction left(){
            return NORTH;
        }

        public Direction right(){
            return SOUTH;
        }

        public Direction opposite(){
            return WEST;
        }
    },
    WEST("left"){
        public Direction left(){
            return SOUTH;
        }

        public Direction right(){
            return NORTH;
        }

        public Direction opposite(){
            return EAST;
        }
    };

    @Override
    public String toString() {
        return directionString;
    }

    public static Direction parseDirection(String directionString) throws IOException {
        switch (directionString){
            case "top" -> {
                return NORTH;
            }
            case "bottom" -> {
                return SOUTH;
            }
            case "right" -> {
                return EAST;
            }
            case "left" -> {
                return WEST;
            }
            default -> throw new IOException("Direction not found");
        }
    }
    String directionString;
    Direction(String directionString) {
        this.directionString = directionString;
    }

    public abstract Direction left();
    public abstract Direction right();
    public abstract Direction opposite();

}
