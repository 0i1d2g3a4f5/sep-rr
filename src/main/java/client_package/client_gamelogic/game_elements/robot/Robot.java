package client_package.client_gamelogic.game_elements.robot;


import client_application.ClientApplication;
import client_package.Client;
import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.game_elements.ElementName;
import client_package.client_gamelogic.game_elements.GameElement;

import gamelogic.Direction;
import gamelogic.Position;

import java.io.IOException;

/**
 * @author Mark Ringer, Qinyi Hui
 * creates a robot character for every player.
 * this class is part of the clientside Gamestate, so it mirrors the serverside gamelogic in most its
 * metodes but serves only for visual representation
 */
public class Robot extends GameElement {

    private boolean isPlaced = false;
    public int figure;
    private Game game = Game.getInstance();
    public Direction getDirectionFacing() {
        return directionFacing;
    }
    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }
    private Direction directionFacing;
    private Position position;
    private String name;
    private CPlayer player;
    public Robot(int figure, CPlayer player) throws IOException {
        super(ElementName.ROBOT);
        visualOrder = 7;
        this.player = player;
        this.figure = figure;
        this.game = Game.getInstance();
    }
    public void setFigure(int f){
        this.figure=f;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isPlaced() {
        return isPlaced;
    }

    /**
     * @author Mark Ringer
     * only use for placing the Robot the first time on GameBoard
     * @param y
     * @param x
     */
    public void placeRobot(int y, int x){
        isPlaced = true;
        directionFacing = Direction.RIGHT;
        try {
            moveRobotTo(y,x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author Mark Ringer
     * used to set move the Robot freely on the Map
     * @param y
     * @param x
     */
    public synchronized void moveRobotTo(int y, int x) throws IOException {
        if(position != null){
            Game.getInstance().getMap().getGameField(position.getY(),position.getX()).removeRobot();
        }
        Game.getInstance().getMap().getGameField(y,x).addRobot(this);
        position = new Position(y,x);
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @author Mark Ringer
     * @param position
     * @param direction
     * @param figure
     * update the location of the robot
     */
    public Robot (Client client, Position position, Direction direction, int figure) throws IOException {
        this.figure = figure;
        game =client.getGame();
        directionFacing = direction;
        this.position = position;
        this.orientations.add(direction);
    }

    public CPlayer getPlayer() {
        return player;
    }

    public void setPlayer(CPlayer player) {
        this.player = player;
    }

    /**
     * performs a left-turn
     * @author Mark Ringer
     * @return boolean
     */
    public boolean left(){

        Client.clientLogger.info("Robot "+figure+" turns left");
        Client.clientLogger.debug("Robot "+ figure+" direction before turn= "+directionFacing);
        directionFacing=directionFacing.left();
        Client.clientLogger.debug("Robot "+ figure+" direction after turn= "+directionFacing);
        return true;
    }

    /**
     * @author Mark Ringer
     * @return boolean
     */
    public boolean right(){
        Client.clientLogger.info("Robot " + figure + " turns right");
        Client.clientLogger.debug("Robot " + figure+ " direction before turn= " + directionFacing);
        directionFacing = directionFacing.right();
        Client.clientLogger.debug("Robot " + figure + " direction after turn = " + directionFacing);
        return true;
    }

    /**
     * @author Mark Ringer
     * @return boolean
     */

    public boolean uTurn(){
        directionFacing = directionFacing.right();
        directionFacing = directionFacing.right();
        return true;
    }



    /**
     * @uthor Ringer
     */
    public void reboot() {}

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

    public void backward(int i) {}

    public void forward(int i) {}

    public int getFigure() {
        return figure;
    }
}
