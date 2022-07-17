package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mark Ringer
 */
public class CPlayer {

    int figure;

    private Client client;

    Game game;
    int EnergyCubes;
    int clientID;
    private Robot robot;
    private int handCardsAmount = 0;
    private Card selectedCard;

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    private ArrayList<Card> handCards= new ArrayList(5);
    private Card[] registerCards = new Card[5];

    public CPlayer(Client client, Game game){
        Client.clientLogger.info("Created CPlayer");
        if(client.getId() == game.getClient().getId()){

            this.game = game;
            //TODO own client needs player
            this.clientID = client.getId();
            client.setPlayer(this);
            this.client = client;
        } else {
            try {
                this.robot = new Robot(figure,this);
                this.getRobot().setFigure(client.getFigure());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.clientID = client.getId();
            this.game = game;
            client.setPlayer(this);
            this.client = client;
        }



    }
    public void placeRegisterCards(Card card, int pos) {
        registerCards[pos] = card;
        if(pos==5){
            client.getClientApplication().activateCardSelection(false);
        }
        selectedCard = null;

    }

    public void selectCard(int posHandcard,int posRegister){
        if(posHandcard>=0 && posHandcard <9){
            selectedCard = handCards.get(posHandcard);

        }

    }
    public void setRegisterCards(Card[] registerCards) {
        this.registerCards = registerCards;
    }

    public void drawDamage(){
        //TODO draw Damage handeling
    }



    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public Game getGame() {
        return game;
    }



    public int getEnergyCubes() {
        return EnergyCubes;
    }
    public Robot getRobot() {
        return robot;
    }

    public void registerCard(Card card, int position){
        registerCards[position]=card;
    }
    public Card[] getRegisterCards(){
        return registerCards;
    }

    public int getHandCardsCount() {
        return handCardsAmount;
    }

    public void addHandCards(int count){
        handCardsAmount += count;
    }

    public void setHandCardsAmount(int handCardsAmount) {
        this.handCardsAmount = handCardsAmount;
    }

    @Override
    public String toString() {
        return client.toString();
    }

    public int getClientID() {
        return clientID;
    }
}
