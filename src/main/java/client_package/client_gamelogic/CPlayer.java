package client_package.client_gamelogic;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;

import java.util.ArrayList;

/**
 * @author Mark Ringer
 */
public class CPlayer {

    int figure;


    Game game;
    int EnergyCubes;
    int clientID;
    private Robot robot;
    private int handCards = 0;
    private ArrayList<Card> registerCards= new ArrayList(9);

    public CPlayer(ClientObject clientObject, Game game){
        if(clientObject.getId() == game.getClient().getId()){
            //TODO own client needs player
            ThisCPlayer thisPlayer = new ThisCPlayer((Client)clientObject,game);
            OtherClient client = (OtherClient) clientObject;
            this.clientID = client.getId();
            client.setPlayer(thisPlayer);
        } else {
            OtherClient client = (OtherClient) clientObject;
            this.robot = new Robot(figure,this);
            this.clientID = client.getId();
            client.setPlayer(this);
        }



    }

    protected CPlayer() {
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
    public Card[] getRegisterCards(){
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
