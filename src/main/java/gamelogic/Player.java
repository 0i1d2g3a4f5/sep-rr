package gamelogic;

import gamelogic.cards.programming_cards.*;
import newmessages.*;
import server_package.SClient;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;
import gamelogic.game_elements.robot.Robot;
import server_package.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import static utility.SearchMethods.searchCard;

/**
 * @author Mark Ringer, Qinyi
 */
public class Player{
    public boolean isProgramming;
   // ClientHandler sClient;

    private int EnergyCube;

    private Card cardImPlaying;
    private Card lastPlayedCard;
    private Game game;
    private Card[] register= new Card[5];
    private Stack<Card> deck = buildDeck();
    private Stack<Card> discardPile = new Stack<>();
    private ArrayList<Card> handCards = new ArrayList<>();
    private Robot robot;

    private SClient sClient;

    private int checkpointTokens;

    //only for testing
    Player() {
    }

    public Card getCardImPlaying() {
        return cardImPlaying;
    }

    public void setCardImPlaying(Card cardImPlaying) {
        this.cardImPlaying = cardImPlaying;
    }

    public SClient getClient() {
        return sClient;
    }


    public Game getGame() {
        return game;
    }

    public Player(SClient sClient, Game game) {
        this.sClient = sClient;
        this.game = game;
        Robot robot = new Robot(game);
        robot.setPlayer(this);
        setRobot(robot);
    }

    public void setLastPlayedCard(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
    }

    /**
     * @author Ringer
     * @param robot
     * select the Robot of a player
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }


    private Stack<Card> buildDeck(){
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i < 2; i++) {
            deck.add(new Again());
        }
        for (int i = 0; i < 1; i++) {
            deck.add(new BackUp());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new LeftTurn());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new MoveOne());
        }
        for (int i = 0; i < 1; i++) {
            deck.add(new MoveThree());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new MoveTwo());
        }
        for (int i = 0; i < 1; i++) {
            deck.add(new PowerUp());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new RightTurn());
        }
        for (int i = 0; i < 1; i++) {
            deck.add(new UTurn());
        }
        deck.forEach((card)->{card.setPlayer(this); card.setGame(game);});

        Collections.shuffle(deck);
        return deck;

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
     * sends a message to the sClient of the CPlayer
     */
    public void sendMessage(Message message){
        sClient.sendSelf(message);
    }

    public boolean placeCard(Card card, int position){
        if(card !=null&&addToRegister(card,position)){
            handCards.remove(card);
            return true;
        }
        return false;
    }

    public void drawSpam(int count){
        for (int i = 0; i < count; i++) {
            discardPile.add(game.getSpamDrawPile().pop());
        }

    }
    public void drawVirus(int count){
        for (int i = 0; i < count; i++) {
            discardPile.add(game.getVirusDrawPile().pop());
        }
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
        if(register[position] != null){
            register[position].activateCard();
        }else {
            System.out.println("register at "+position+ "is null");
        }

    }

    public Card getRegister(int position){
        return register[position];
    }

    /**
     * @author Ringer
     * moves all cards from registry to discardPile
     */
    public void clearAllRegister(){
        Server.serverLogger.debug("Before ClearAllRegisters: \n" +
                "   Register: "+ Arrays.toString(register)+"\n" +
                "   DiscardPile: "+ discardPile);
        for (int i = 0;i< register.length;i++) {

            Card card = register[i];
            if(card !=null){
                discardPile.add(card);
                register[i] = null;
            }


        }
        Server.serverLogger.debug("After ClearAllRegisters: \n" +
                "   Register: "+ Arrays.toString(register)+"\n" +
                "   DiscardPile: "+discardPile);
    }

    public void clearThisRegister(int pos){
        if((register.length >pos )&&( pos>0))
        register[pos] = null;
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

    /**
     * @author Qinyi
     * get the energyspace
     */
    public void addEnergyCube(){
        EnergyCube ++;

    }
    public int getEnergyCube(){
        return EnergyCube;
    }

    /**
     * @author Qinyi build card Piles
     * @return
     */
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
        for (int i = 0; i < handCards.size(); i++) {
            Card card = handCards.get(i);
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
            sClient.getServer().getGame().sendToAllPlayers(new MessageShuffleCoding(sClient.getId()));
        }
        Card card = deck.pop();
        return card;
    }

    /**
     * @author Ringer
     */
    private void refillDeck(){

        deck = discardPile;
        discardPile= new Stack<>();
        Collections.shuffle(deck);
        Server.serverLogger.debug("Refilled deck"+ deck);
    }

    @Override
    public String toString() {
        return "Player{" +
                "sClient=" + sClient +
                '}';
    }
}
