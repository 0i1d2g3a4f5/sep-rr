package client_package.client_gamelogic;

import client_package.client_gamelogic.cards.Card;
import gamelogic.Color;

import java.util.ArrayList;

public class ThisPlayer extends Player{
    private ArrayList<Card> handCards;

    public ThisPlayer(Color robotColor) {
        super(robotColor);
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }


}
