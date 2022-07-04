package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import gamelogic.Color;
import newmessages.MessageSelectedCard;

import java.util.ArrayList;

public class ThisPlayer extends Player{
    private Card selectedCard;

    private Client client;

    private Robot robot;
    private static ArrayList<Card> registerCards;
    private static ArrayList<Card> handCards;

    public ThisPlayer(int id, Color robotColor,Client client) {
        super(id,robotColor);
        this.client = client;
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

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void placeRegisterCards(Card card,int pos) {
        registerCards.add(pos,card);
    }

    public void selectCard(int posHandcard,int posRegister){
        if(posHandcard>=0 && posHandcard <9){
            client.sendSelf(new MessageSelectedCard(handCards.get(posHandcard).toString(),posRegister));
        }

    }

    public ArrayList<Card> getRegisterCards(){
        return registerCards;
    }


}
