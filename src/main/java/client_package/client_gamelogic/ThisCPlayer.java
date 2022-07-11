package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import newmessages.Message;
import newmessages.MessageSelectedCard;

import java.io.IOException;
import java.util.ArrayList;

public class ThisCPlayer extends CPlayer {
    private Card selectedCard;

    private Client client;

    private Robot robot;
    private Card[] registerCards = new Card[5];
    public ArrayList<Card> handCards = new ArrayList<>();

    public ThisCPlayer(Client client, Game game) {
        super(client,game);

        this.client = client;
        try {
            this.robot = new Robot(client.getFigure(),this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.game = game;

    }

    public void setRegisterCards(Card[] registerCards) {
        this.registerCards = registerCards;
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

            Message message = new MessageSelectedCard(handCards.get(posHandcard).toString(),posRegister);
            client.sendSelf(message);
            System.out.println("SENT: "+ message);
            selectedCard = handCards.get(posHandcard);
        }

    }

    public Card[] getRegisterCards(){
        return registerCards;
    }


}