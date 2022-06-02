package gamelogic.Robot;

import gamelogic.Direction;
import gamelogic.Direction.*;
import gamelogic.Game;
import gamelogic.Position;

/**
 * @author Qinyi
 * create a robot character for every player
 */
public class Robot {

    Game game;

    Direction directionFacing;
    Position position;
    private String name;

    private boolean isAlive;

    private Position nextPosition;
    private Direction nextDirectionFacing;

    /**
     * update the state and the location of the robot
     * @param position
     */


    public Robot (Position position){
        game =Game.getInstance();
        this.position = position;
    }


    public boolean left(){
        directionFacing = directionFacing.left();
        return true;
    }
    public boolean right(){
        directionFacing = directionFacing.right();
        return true;
    }

    public boolean forward(){

        return move(1);
    }
    public boolean backward(){

        return move(-1);
    }
    private boolean move(int gear){

        switch (directionFacing){

            case NORTH -> {
                nextPosition = new Position(position.getY()+1, position.getX());
            }
            case SOUTH -> {
                nextPosition = new Position(position.getY()-1, position.getX());
            }
            case EAST -> {
                nextPosition = new Position(position.getY(), position.getX()+1);
            }
            case WEST -> {
                nextPosition = new Position(position.getY(), position.getX()-1);
            }
            default -> {
                return false;
            }
        }
        return true;
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
/*
    public Location getLocation(){
        return this.state.getRobot().getLocation();
    }

 */


}
