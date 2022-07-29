package utility;

import gamelogic.Position;

/**
 * @author Mark Ringer
 */
public class MyMath {
    public static double pythagoras(Position pos1, Position pos2){
        double sideA = pos1.getX()-pos2.getX();
        double sideB = pos1.getY()-pos2.getY();
        return Math.sqrt(Math.pow(sideA,2)+Math.pow(sideB,2));
    }
}
