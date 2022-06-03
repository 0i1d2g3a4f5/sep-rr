package gamelogic.Robot;

import gamelogic.Direction;
import gamelogic.Direction.*;
import gamelogic.Game;
import gamelogic.Position;
import gamelogic.map.GameField;

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

    public boolean uTurn(){
        directionFacing = directionFacing.opposite();
        return true;
    }

    public boolean forward(){

        return move(1);
    }
    public boolean backward(){

        return move(-1);
    }

    /**
     *
     * @param gear is The Direction
     * @return
     */
    private boolean move(int gear){
        setNextPosition(gear);
        if(checkNextPosition())
            changePositionOnBoard();

        return true;
    }

    private boolean changePositionOnBoard() {
        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        nextField.setRobot(this);
        currentField.removeRobot();
        position = nextPosition;
        nextPosition = null;
        return true;
    }

    private boolean checkNextPosition() {
        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);
        switch (directionFacing){
            case NORTH -> {
                if(currentField.isWallNorth() || nextField.isWallSouth()) return false;
                return true;
            }
            case SOUTH -> {
                if(currentField.isWallSouth() || nextField.isWallNorth()) return false;
                return true;
            }
            case EAST -> {
                if(currentField.isWallEast() || nextField.isWallWest()) return false;
                return true;
            }
            case WEST -> {
                if(currentField.isWallWest() || nextField.isWallEast()) return false;
                return true;
            }
            default -> {
                return false;
            }
        }

    }

    private boolean setNextPosition(int gear) {
        switch (directionFacing){

            case NORTH -> {
                nextPosition = new Position(position.getY()+gear, position.getX());
            }
            case SOUTH -> {
                nextPosition = new Position(position.getY()-gear, position.getX());
            }
            case EAST -> {
                nextPosition = new Position(position.getY(), position.getX()+gear);
            }
            case WEST -> {
                nextPosition = new Position(position.getY(), position.getX()-gear);
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



}
