package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import newmessages.Message;
import newmessages.MessageSelectedCard;

import java.util.ArrayList;

public class ThisCPlayer extends CPlayer {
    private Card selectedCard;

    private Client client;

    private Robot robot;
    private Card[] registerCards = new Card[5];
    public ArrayList<Card> handCards;

    public ThisCPlayer(Client client, Game game) {
        super(client,game);

        this.client = client;
        this.robot = new Robot(client.getFigure(),this);
        this.game = game;

    }



    @Override
    public Robot getRobot() {
        return robot;
    }

    public void drawDamage(){
        //TODO draw Damage handeling
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public  ArrayList<Card> getHandCards() {
        return handCards;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void placeRegisterCards(Card card, int pos) {
        //System.out.println("Placing Card: "+card);
        registerCards[pos] = card;
        selectedCard = null;

    }

    public void selectCard(int posHandcard,int posRegister){
        if(posHandcard>=0 && posHandcard <9){
            /*
            Message message = new MessageSelectedCard(handCards.get(posHandcard).toString(),posRegister);
            client.sendSelf(message);
            System.out.println("SENT: "+ message);*/
            selectedCard = handCards.get(posHandcard);
        }

    }

    public Card[] getRegisterCards(){
        return registerCards;
    }


}