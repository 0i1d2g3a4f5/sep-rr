package gamelogic.game_elements.robot;

import gamelogic.*;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;
import gamelogic.game_elements.RestartPoint;
import gamelogic.map.GameField;
import newmessages.MessageMovement;
import newmessages.MessagePlayerTurning;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Qinyi, Mark
 * creates a robot character for every player
 */
public class Robot extends GameElement implements RobotMovement, Activatable {



    private final Color color;
    private Game game;

    private Direction directionFacing;


    private Position position;
    private String name;

    private boolean isAlive;



    private Player player;

    private Position nextPosition;
    private Direction nextDirectionFacing;

    public Color getColor() {
        return color;
    }
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    /**
     * @author Ringer
     * update the location of the robot
     * @param position
     */
    public Robot (Position position, ArrayList<Direction> orientations,Color color){

        //TODO Change ArrayList<Direction> orientations to Driection direction facing and foill orientations after
        game =Game.getInstance();
        this.position = position;
        this.orientations = orientations;
        this.color = color;
    }

    public void takeDamage(int count) {
        //TODO player.sendMessage(), Spam Card
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean left(){
        directionFacing = directionFacing.left();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"counterclockwise"));

        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean right(){
        directionFacing = directionFacing.right();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */

    public boolean uTurn(){
        directionFacing = directionFacing.opposite();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        return true;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean forward(int distance){
        Position oldPos = position.clone();
        boolean success = true;
        for (int i = 0;i<distance;i++){
            if(!move(1)){
                success = false;
            }
        }
        //TODO game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getX() - oldPos.getX(), position.getY() - oldPos.getY()));
        return success;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean backward(int distance){
        Position oldPos = position.clone();
        boolean success = true;
        for (int i = 0;i<distance;i++){
            if(!move(-1)){
                success = false;
            }
        }
        //game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getX() - oldPos.getX(), position.getY() - oldPos.getY()));
        return success;
    }

    /**
     * @auther Ringer
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

    /**
     * @author Ringer
     * @param targetDirection
     * @return
     */
    public boolean displace(Direction targetDirection){
        //TODO better name
        setNextPosition(targetDirection);

        if(checkNextPosition(targetDirection))
            changePositionOnBoard();

        return true;
    }

    /**
     * @author Ringer
     * @return
     */
    private boolean changePositionOnBoard() {

        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        if(!nextField.addRobot(this)) return false;
        currentField.removeRobot();
        position = nextPosition;
        nextPosition = null;
        gameField = game.board.getField(position);

        player.getClient().sendAll(new MessageMovement(player.getClient().getId(),position.getX(),position.getY()));

        return true;
    }

    /**
     * @author Ringer
     * @param targetDirection
     * @return
     */
    private boolean checkNextPosition(Direction targetDirection) {

        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        if (currentField.checkWall(targetDirection) || nextField.checkWall(targetDirection.opposite()))
            return false;
        if(nextField.contains(ElementName.PIT)||nextField==null){
            reboot();
            return false;
        }


        return true;
    }

    /**
     * @uthor Ringer
     */
    public void reboot(){
        //TODO Check rules if Register is continued


        for(RestartPoint restartPoint:game.board.restartPoints){
            if(restartPoint.getIsOnBoard()==isOnBoard){
                getGameField().removeRobot();
                restartPoint.getGameField().addRobot(this);
                gameField = restartPoint.getGameField();
                position = gameField.getPosition();
                player.getClient().sendAll(new MessageMovement(player.getClient().getId(),position.getX(),position.getY()));

            }
        }

    }

    /**
     * @author Ringer
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
        //TODO activate Robot Lasers
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
