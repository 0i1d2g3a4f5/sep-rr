package gamelogic;

/**
 * @author Qinyi
 * to make the direction of the robot's approaching clear
 */
public enum Direction{
    NORTH{
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

    SOUTH{
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

    EAST{
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
    WEST{
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

    public abstract Direction left();
    public abstract Direction right();
    public abstract Direction opposite();

}
