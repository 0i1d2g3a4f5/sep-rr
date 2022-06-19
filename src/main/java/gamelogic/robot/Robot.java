package gamelogic.robot;

import gamelogic.*;
import gamelogic.game_elements.ConveyorBelt;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;
import gamelogic.game_elements.Wall;
import gamelogic.map.GameField;

import java.io.IOException;

import static gamelogic.Direction.*;

/**
 * @author Qinyi, Mark
 * create a robot character for every player
 */
public class Robot extends GameElement implements RobotMovement, Activatable {

    Game game;

    Direction directionFacing;
    Position position;
    private String name;

    private boolean isAlive;

    private Position nextPosition;
    private Direction nextDirectionFacing;

    /**
     * update the location of the robot
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
        Direction targetDirection = gear == 1 ? directionFacing : directionFacing.opposite();
        setNextPosition(targetDirection);

        if(checkNextPosition(targetDirection))
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

    private boolean checkNextPosition(Direction targetDirection) {

        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        if (currentField.checkWall(targetDirection) || nextField.checkWall(targetDirection.opposite()))
            return false;

        return true;
    }

    /**
     * set the next Position if a robot meet the gear
     * @param targetDirection
     * @return
     */
    private boolean setNextPosition(Direction targetDirection) {
        switch (targetDirection){

            case NORTH -> nextPosition = new Position(position.getY()+1, position.getX());
            case SOUTH -> nextPosition = new Position(position.getY()-1, position.getX());
            case EAST -> nextPosition = new Position(position.getY(), position.getX()+1);

            case WEST -> nextPosition = new Position(position.getY(), position.getX()-1);

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


    @Override
    public void activate() {

    }

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT, GEAR, PUSHPANEL,ENERGYSPACE -> {
                return 1;
            }
            case CONVEYORBELT, LASER -> {
                    return -1;
            }
            case ROBOT -> {
                return 0;
            }
            default -> {
                try {
                    throw new IOException(o.getType()+" is Not Comparable");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
