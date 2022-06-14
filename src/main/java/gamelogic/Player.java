package gamelogic;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.playableInRegister;
import gamelogic.robot.Robot;
import newmessages.Message;
import server.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static utility.CardUtility.searchCard;

/**
 * @author Mark Ringer
 */
public class Player{
    int numberOfHearts;
    public boolean isProtected;
   // ClientHandler client;
    boolean myTurn = false;

    private int EnergyCube;
    private Card lastPlayedCard;
    private Direction direction;

    private Stack<Card> deck;
    private Stack<Card> discardPile;
    private ArrayList<Card> handCards;
    private Robot robot;

    //only for testing
    Player() {

    }

    public Client getClient() {
        return client;
    }


    private Client client;

    public Player(Client client) {
        this.client = client;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /* public ClientHandler getClient() {
        return client;
    }

    public Player(ClientHandler client) {
        this.client = client;
    }


    */
    /**
     * @return
     */
    private ArrayList<Card> register= new ArrayList<Card>();

    public ArrayList<Card> getRegister() {
        return register;
    }

    public boolean addToRegister(Card card){
        if(checkRegister(card)){
            register.add( card);
        }
        return true;
    }

    public void sendMessage(Message message){
        client.sendSelf(message);
    }

    public void playCard(CardName cardName){
        Card card = searchCard(cardName,handCards);
        handCards.remove(card);



    }



    public void clearRegister(){
        for (Card card:register) {
            discardPile.add(card);
            register.remove(card);
        }
    }

    private boolean checkRegister(Card card){
        if(register.size()>=5){
            return false;
        } else if(!(card instanceof playableInRegister)){
            return false;
        } else {
            return true;
        }
    }

    public void addEnergyCube(){
        EnergyCube ++;
    }
    public int getEnergyCube(){
        return EnergyCube;
    }

    public  Card getLastPlayedCard(){
        return lastPlayedCard;
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
    }
    public Stack<Card> DeckPile(){return deck;}

    public ArrayList<Card> getHandCards(){
        return handCards;
    }

    public Robot getRobot(){
        return robot ;
    }


    public void placeRobot() {

    }

}
