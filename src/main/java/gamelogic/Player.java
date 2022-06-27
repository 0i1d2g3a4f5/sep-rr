package gamelogic;

import newmessages.*;
import server_package.Client;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;
import gamelogic.game_elements.robot.Robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static utility.SearchMethods.searchCard;

/**
 * @author Mark Ringer
 */
public class Player{
    public boolean isProgramming;
   // ClientHandler client;

    private int EnergyCube;
    private Card lastPlayedCard;
    private Game game;
    private Card[] register= new Card[5];
    private Stack<Card> deck;
    private Stack<Card> discardPile;
    private ArrayList<Card> handCards;
    private Robot robot;

    private Client client;

    private int checkpointTokens;

    //only for testing
    Player() {
    }

    public Client getClient() {
        return client;
    }




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
        //TODO ArrayList or Array?
        return register;
    }

    public int getCheckpointTokens() {
        return checkpointTokens;
    }

    public void addCheckpointToken() {
        this.checkpointTokens++;
    }

    /**
     * used by place card
     * @param card
     * @param position
     * @return
     */
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
    //TODO Check use
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

    /**
     * @author Ringer
     */
    public void drawCards() {
        while(handCards.size()<9){
            handCards.add(drawCard());
        }

    }



    /**
     * @uthor Ringer
     */
    public void discardAllHandCards(){
        for (Card card:handCards) {
            handCards.remove(card);
            discardPile.add(card);
        }
    }
    /**
     * @author Ringer
     */
    public Card drawCard(){
        if(deck.empty()) {
            refillDeck();
            client.getServer().getGame().sendToAllPlayers(new MessageShuffleCoding(client.getId()));
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
