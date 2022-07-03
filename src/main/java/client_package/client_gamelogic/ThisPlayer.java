package client_package.client_gamelogic;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import gamelogic.Color;

import java.util.ArrayList;

public class ThisPlayer{

    private Robot robot;
    private ArrayList<Card> registerCards;
    private ArrayList<Card> handCards;

    public ThisPlayer(Color robotColor) {
        this.robot = new Robot(robotColor);

    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setRegisterCards(ArrayList<Card> registerCards) {
        this.registerCards = registerCards;
    }

    public ArrayList<Card> getRegisterCards(){
        return registerCards;
    }


}
