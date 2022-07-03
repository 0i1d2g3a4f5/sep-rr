package client_package.client_gamelogic.game_elements.game_elements.robot;

import client_package.client_gamelogic.game_elements.game_elements.GameElement;
import gamelogic.*;
import newmessages.*;
import server_package.gamelogic.*;
import server_package.newmessages.MessageReboot;

/**
 * @author Qinyi, Mark
 * creates a robot character for every player
 */
public class Robot extends GameElement {

    private final Color color;
    private Game game;

    private Direction directionFacing;
    private Position position;
    private String name;
    private Player player;

    public Robot() {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public Position getPosition() {
        return position;
    }


    private void setPosition(Position position) {
        this.position = position;
    }


    /**
     * @param position
     * @param direction
     * @author Ringer
     * update the location of the robot
     */
    public Robot (Position position, Direction direction, Color color){
        game =Game.getInstance();
        directionFacing = direction;
        this.position = position;
        this.orientations.add(direction);
        this.color = color;
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
        //TODO Animation
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean right(){
        //TODO Animation
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */

    public boolean uTurn(){
        //TODO Animation
        return true;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean forward(int distance){
        //TODO Animation
        return true;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean backward(int distance){
        //TODO Animation
        return true;
    }

    /**
     * @uthor Ringer
     */
    public void reboot() {
        //TODO Check rules if Register is continued
        game.sendToAllPlayers(new MessageReboot(player.getClient().getId()));
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

}