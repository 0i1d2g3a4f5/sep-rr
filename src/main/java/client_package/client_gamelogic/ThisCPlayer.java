package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import newmessages.MessageSelectedCard;

import java.util.ArrayList;

public class ThisCPlayer extends CPlayer {
    private Card selectedCard;

    private Client client;

    private Robot robot;
    private static ArrayList<Card> registerCards;
    private static ArrayList<Card> handCards;

    public ThisCPlayer(Client client, Game game) {
        this.client = client;
        this.robot = new Robot(client.getFigure(),this);

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
