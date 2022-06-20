package gamelogic;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;
import gamelogic.game_elements.robot.Robot;
import newmessages.Message;
import server.Client;

import java.util.ArrayList;
import java.util.Stack;

import static utility.SearchMethods.searchCard;

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

    private Game game;
    private Card[] register= new Card[5];
    private Stack<Card> deck;
    private Stack<Card> discardPile;
    private ArrayList<Card> handCards;
    private Robot robot;


    private int checkpointTokens;

    //only for testing
    Player() {

    }

    public Client getClient() {
        return client;
    }


    private Client client;

    public Player(Client client,Game game) {
        this.client = client;
        this.game = game;
    }

    /**
     * @author Ringer
     * @param robot
     * select the Robot of a player
     */
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


    public Card[] getAllRegisters() {
        return register;
    }

    public int getCheckpointTokens() {
        return checkpointTokens;
    }

    public void addCheckpointToken() {
        this.checkpointTokens++;
    }

    public boolean addToRegister(Card card,int position){
        if(checkRegister(card,position)){
            register[position] = card;
        }
        return true;
    }

    /**
     * @author Ringer
     * @param message
     * sends a message to the client of the Player
     */
    public void sendMessage(Message message){
        client.sendSelf(message);
    }

    /**
     * @author Ringer
     * allows cards to be played by the Player
     * @param cardName
     * @param position
     * @return
     */
    public boolean playCard(CardName cardName,int position){

            Card card = searchCard(cardName,handCards);
            if(card !=null&&addToRegister(card,position)){
                handCards.remove(card);
                return true;
            }
            return false;
    }

    private void finishProgramming() throws InterruptedException {
        game.setProgrammingPhase(false);
    }

    /**
     * @author Ringer
     * @param position
     * activates the register ot position
     */
    public void activateRegister(int position){
        register[position].activateCard();
    }

    public Card getRegister(int position){
        return register[position];
    }

    /**
     * moves all cards from registry to discardPile
     */
    public void clearRegister(){
        for (int i = 0;i< register.length;i++) {
            Card card = register[i];
            discardPile.add(card);
            register[i] = null;
        }
    }

    /**
     * checks if a card can be played
     * @author Ringer
     * @param card
     * @param position
     * @return
     */
    private boolean checkRegister(Card card,int position){
        if(!(card instanceof PlayableInRegister)) {
            return false;
        }else if(register[position]!=null){
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
