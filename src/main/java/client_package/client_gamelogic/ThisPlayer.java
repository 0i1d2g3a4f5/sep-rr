package client_package.client_gamelogic;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import gamelogic.Color;

import java.util.ArrayList;

public class ThisPlayer extends Player{

    private Robot robot;
    private static ArrayList<Card> registerCards;
    private static ArrayList<Card> handCards;

    public ThisPlayer(int id, Color robotColor) {
        super(id,robotColor);
    }

    public void drawDamage(){
        //TODO draw Damage handeling
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public static ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setRegisterCards(ArrayList<Card> registerCards) {
        this.registerCards = registerCards;
    }

    public ArrayList<Card> getRegisterCards(){
        return registerCards;
    }


}
