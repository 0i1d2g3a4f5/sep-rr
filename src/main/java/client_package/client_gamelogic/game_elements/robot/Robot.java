package client_package.client_gamelogic.game_elements.robot;


import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.game_elements.GameElement;

import gamelogic.Direction;
import gamelogic.Position;

import java.io.IOException;

/**
 * @author Qinyi, Mark
 * creates a robot character for every player
 */
public class Robot extends GameElement {

    //private final Color color;

    public final int figure;
    private Game game;

    private Direction directionFacing;
    private Position position;
    private String name;
    private CPlayer player;

    public Robot(int figure, CPlayer player) {
        this.player = player;
        this.figure = figure;
    }

    public Position getPosition() {
        return position;
    }

    public void moveRobotTo(int y, int x){
        game.getMap().getGameField(position.getY(),position.getX()).removeRobot();
        game.getMap().getGameField(y,x).removeRobot();
        game.getMap().getGameField(y,x).addRobot(this);
        position = new Position(y,x);


    }


    private void setPosition(Position position) {
        this.position = position;
    }


    /**
     * @param position
     * @param direction
     * @param figure
     * @author Ringer
     * update the location of the robot
     */
    public Robot (Position position, Direction direction, int figure) throws IOException {
        this.figure = figure;
        game =Game.getInstance();
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
     * @author Ringer
     * @return boolean
     */
    public boolean left(){
        //TODO Animation
        directionFacing.left();
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean right(){
        //TODO Animation
        directionFacing.right();
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */

    public boolean uTurn(){
        //TODO Animation
        directionFacing.right();
        directionFacing.right();
        return true;
    }



    /**
     * @uthor Ringer
     */
    public void reboot() {
        //TODO Check rules if Register is continued
        //TODO case not answered
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

    public void backward(int i) {
    }

    public void forward(int i) {
    }
}
