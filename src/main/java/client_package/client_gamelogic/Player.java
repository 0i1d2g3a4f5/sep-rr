package client_package.client_gamelogic;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import gamelogic.Color;

import java.util.ArrayList;

public class Player {
    private Robot robot;
    private int handCards = 0;
    private ArrayList<Card> registerCards= new ArrayList(9);

    public Player(Color robotColor){

        this.robot = new Robot(robotColor);

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

    public int getHandCards() {
        return handCards;
    }
}
