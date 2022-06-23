package gamelogic;

import newmessages.MessageCardSelected;
import server_package.Client;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;
import gamelogic.game_elements.robot.Robot;
import newmessages.Message;
import newmessages.MessageCardPlayed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static utility.SearchMethods.searchCard;

/**
 * @author Mark Ringer
 */
public class Player{
    int numberOfHearts;
    public boolean isProtected;

    public boolean isProgramming;
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

    public Player(Client client, Game game) {
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

    private boolean addToRegister(Card card,int position){
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
     * allows cards to be placed by the Player
     * @param cardName
     * @param position
     * @return
     */
    public boolean placeCard(CardName cardName, int position){

            Card card = searchCard(cardName,handCards);
            if(card !=null&&addToRegister(card,position)){
                handCards.remove(card);
                return true;
            }
            return false;
    }

    public boolean removeCard(int position){

        Card card = register[position];
        if(card != null){
            handCards.add(card);
            register[position]=null;
            return true;
        }
        return false;
    }


    /**
     * @author Ringer
     */
    private int findNextFreeRegister(){
        for (int i = 0; i < register.length; i++) {
            if(register[i] == null) return i;
        }
        return -1;
    }

    /**
     * @author Ringer
     */
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
     * @author Ringer
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

    /**
     * @author Ringer
     */
    public boolean placeRobot(Position position) {
        return game.placeRobot(this,position);
    }


    public void drawCards() {
        while(handCards.size()<9){
            handCards.add(drawCard());
        }

    }
    /**
     * @author Ringer
     */
    private Card drawCard(){
        if(deck.empty()) {
            refillDeck();
        }
        Card card = deck.pop();
        return card;
    }

    /**
     * @author Ringer
     */
    private void refillDeck(){

        deck = discardPile;
        discardPile.clear();
        Collections.shuffle(deck);
    }
}
