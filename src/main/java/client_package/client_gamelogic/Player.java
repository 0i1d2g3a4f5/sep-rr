package client_package.client_gamelogic;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import gamelogic.Color;

import java.util.ArrayList;

/**
 * @author Mark Ringer
 */
public class Player {



    int EnergyCubes;
    int clientID;
    private Robot robot;
    private int handCards = 0;
    private ArrayList<Card> registerCards= new ArrayList(9);

    public Player(Color robotColor){

        this.robot = new Robot(robotColor);

    }

    public Player() {
    }

    public int getEnergyCubes() {
        return EnergyCubes;
    }
    public Robot getRobot() {
        return robot;
    }

    public void registerCard(Card card, int position){
        registerCards.add(position,card);
    }
    public ArrayList<Card> getRegisterCards(){
        return registerCards;
    }

    public int getHandCardsCount() {
        return handCards;
    }

    public void addHandCards(int count){
        handCards += count;
    }

    public void setHandCards(int handCards) {
        this.handCards = handCards;
    }

    public int getClientID() {
        return clientID;
    }
}
