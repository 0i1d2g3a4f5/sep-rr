package gamelogic;



import gamelogic.Card.Card;
import gamelogic.robot.Robot;

import java.util.Stack;

/**
 * @author Mark Ringer
 */
public class Player{
    int numberOfHearts;
    public boolean isProtected;
   // ClientHandler client;
    boolean myTurn = false;

    private int EnergyCube;
    private Card lastPlayedCard;
    private Direction direction;

    Stack<Card> deck;
    Stack<Card> discardPile;
    Stack<Card> handCards;
    Robot robot;

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /* public ClientHandler getClient() {
        return client;
    }

    public Player(ClientHandler client) {
        this.client = client;
    }


    */
    /**
     * @return
     */


    public void addEnergyCube(){
        EnergyCube ++;
    }
    public int getEnergyCube(){
        return EnergyCube;
    }

    public  Card getLastPlayedCard(){
        return lastPlayedCard;
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    public Direction getRobotDirection(){
        direction = Robot.getDirectionFacing();
        return direction;
    }

    public Stack<Card> getHandCards(){
        return handCards;
    }


}
