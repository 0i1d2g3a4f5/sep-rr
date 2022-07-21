package gamelogic;

import java.io.IOException;

/**
 * @author Qinyi
 * to make the direction of the robot's approaching clear
 */
public enum Direction{
    TOP("top"){
        public Direction left(){
            return LEFT;
        }

        public Direction right(){
            return RIGHT;
        }

        public Direction opposite(){
            return BOTTOM;
        }
    },

    BOTTOM("bottom"){
        public Direction left(){
            return RIGHT;
        }

        public Direction right(){
            return LEFT;
        }

        public Direction opposite(){
            return TOP;
        }
    },

    RIGHT("right"){
        public Direction left(){
            return TOP;
        }

        public Direction right(){
            return BOTTOM;
        }

        public Direction opposite(){
            return LEFT;
        }
    },
    LEFT("left"){
        public Direction left(){
            return BOTTOM;
        }

        public Direction right(){
            return TOP;
        }

        public Direction opposite(){
            return RIGHT;
        }
    };

    @Override
    public String toString() {
        return directionString;
    }

    public static Direction parseDirection(String directionString) throws IOException {
        switch (directionString){
            case "top" -> {
                return TOP;
            }
            case "bottom" -> {
                return BOTTOM;
            }
            case "right" -> {
                return RIGHT;
            }
            case "left" -> {
                return LEFT;
            }
            default -> throw new IOException("Direction "+directionString+" not found");
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
