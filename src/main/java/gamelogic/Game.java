package gamelogic;


import gamelogic.cards.CardName;
import gamelogic.game_elements.ElementName;
import server_package.advancedServer.AdvancedClient;
import gamelogic.cards.Card;
import gamelogic.cards.DeckSerializer;
import gamelogic.cards.damage_card.*;
import gamelogic.cards.damage_card.TrojanHorse;
import gamelogic.map.MapName;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameBoard;
import gamelogic.map.ModelLoader;
import newmessages.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Mark Ringer
 */
public class Game {


    private int robotsPlaced = 0;
    private boolean programmingPhase = false;
    private boolean continueGame = true;
    private ArrayList<Activatable> elementRegistry;
    private static Game instance;

    private Stack<Spam> spamDrawPile;
    private Stack<TrojanHorse> trojanHorseDrawPile;
    private Stack<Virus> virusDrawPile;
    private Stack<Worm> wormDrawPile;
    private MapName mapName;
    private Stack<Card> upgradeWarehouse;
    private ArrayList<Card> upgradeShop;
    public List<Player> playerList = new ArrayList<>();
    public Stack<Spam> getSpamDrawPile(){
        return spamDrawPile;
    }
    public Stack<TrojanHorse> getTrojanHorseDrawPile(){
        return trojanHorseDrawPile;
    }
    public Stack<Virus> getVirusDrawPile(){
        return virusDrawPile;
    }
    public Stack<Worm> getWormDrawPile(){
        return wormDrawPile;
    }
    public static void deleteInstance(){
        instance=null;
    }
    public static Game getInstance(){
        if(instance==null){
            instance = new Game();
        }
        return instance;
    }
    public static boolean instanceExists(){
        return instance!=null;
    }
    private Game() {
    }

    public MapName getMapName() {
        return mapName;
    }


    public void setMapName(MapName mapName) {
        this.mapName = mapName;
    }
    public boolean getContinueGame() {
        return continueGame;
    }
    /**
     * players join the game
     *
     * @return
     */
    public boolean isProgramingPhase() {
        return programmingPhase;
    }

    public synchronized void setProgrammingPhase(boolean programmingPhase) {
        this.programmingPhase = programmingPhase;
    }
    public GameBoard board;
    public Player join(AdvancedClient client) {
        Player player = new Player(client,this);
        for (Player existingPlayer : playerList) {
            if (existingPlayer.getClient().getId() == client.getId()) {
                return null;
            }

        }
        playerList.add(player);
        return player;
    }

    /**
     * @author Ringer
     * Does the setup for a new Game, loads the map and creates the different
     * card Decks
     */
    public void setup() throws IOException, InterruptedException {
        sendToAllPlayers(new MessageActivePhase(0));
        DeckSerializer deckSerializer = new DeckSerializer();
        //select map
        ModelLoader loader = new ModelLoader();
        try {
            board = loader.loadMap("dizzy_highway.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        elementRegistry = board.getRegistry();
        for (Player player :playerList) {
            player.setRobot(new Robot(new Position(1,1)));
        }

        //TODO special cards

        //TODO setup Damage cards
        //Setup Spam
        for(int i = 0; i < 38; i++)
            spamDrawPile.add(new Spam());
        //Setup Virus
        for(int i = 0; i < 18; i++)
            virusDrawPile.add(new Virus());
        //Setup TrojanHorse
        for(int i = 0; i < 12; i++)
            trojanHorseDrawPile.add(new TrojanHorse());
        //Setup Worm
        for(int i = 0; i<6; i++)
            wormDrawPile.add(new Worm());

        //setup upgradeWarehouse
        upgradeWarehouse = deckSerializer.builtDeck(mapName.toString());

        //setup upgradeShop
        upgradeShop = new ArrayList<Card>();
        for(int i = 0;i< playerList.size();i++){
            upgradeShop.add(upgradeWarehouse.pop());
        }

        //TODO setup Checkpoint Tokens

        //Timer
        //TODO setup Energy cubes


        //TODO place Robot
        while(robotsPlaced<playerList.size()) {
            wait();
        }
    }

    public synchronized boolean placeRobot(Player player,Position position){
        if(board.getField(position).contains(ElementName.ROBOT)) return false;
        else if(board.getField(position).contains(ElementName.STARTPOINT)){
            board.getField(position).addElement(player.getRobot());
            robotsPlaced++;
            return true;
        } else return false;
    }

    public void startGame() throws IOException, InterruptedException {
        setup();
        continueGame=true;
        gameLoop();

    }

    private void drawCards(){
        for (Player player:playerList) {
            player.drawCards();
            ArrayList<Card> handCards= player.getHandCards();
            CardName[] cardNames = new CardName[handCards.size()];

            for (int i = 0; i < cardNames.length; i++) {
                cardNames[i] = handCards.get(i).getCardName();
            }
            player.sendMessage(new MessageYourCards(player.getClient().getId(),cardNames));
            for (Player otherPlayer:playerList) {
                if(player.getClient().getId()!= otherPlayer.getClient().getId())
                player.sendMessage(new MessageNotYourCards(player.getClient().getId(), cardNames.length));
            }
        }
    }

    public void gameLoop() throws InterruptedException {
        while (continueGame){
            drawCards();
            /*
            will be added later on
            upgradePhase();
             */
            programmingPhase();
            activationPhase();
        }

    }

    /**
     * Every player can buy upgrades with EnergyCubes
     */
    private void upgradePhase(){
        sendToAllPlayers(new MessageActivePhase(1));
        if(upgradeShop.size()== playerList.size()){
            for (Card card:upgradeShop) {
                card.discard();
            }
        }
        while(upgradeShop.size()< playerList.size()){
            upgradeShop.add(upgradeWarehouse.pop());
        }

    }


    /**
     * @author Ringer
     * Draw cards and arrange them
     */
    private void programmingPhase() throws InterruptedException {
        programmingPhase =true;
        for (Player player:playerList) {
            player.sendMessage(new MessageActivePhase(2));
            player.isProgramming = true;
        }
        while(programmingPhase){
            wait();
        }
        endProgrammingPhase();
    }

    /**
     * Informs all players, that the Timer has started and force-ends their programming Phase
     * @throws InterruptedException
     */
    public void endProgrammingPhase() throws InterruptedException {

        sendToAllPlayers(new MessageTimerStarted());
        TimeUnit.SECONDS.sleep(30);
        sendToAllPlayers(new MessageTimerEnded(programmingPlayers()));
    }

    private ArrayList<Player> generatePlayerActivationList(){
        ArrayList<Player> activationList = new ArrayList<>(playerList);
        Collections.sort(activationList, new Comparator<Player>() {
            @Override
            public int compare(Player thisPlayer, Player thatPlayer) {
                if(board.getAntenna().calculateDistance(thisPlayer.getRobot())>board.getAntenna().calculateDistance(thatPlayer.getRobot()))
                    return -1;
                else if (board.getAntenna().calculateDistance(thisPlayer.getRobot())<board.getAntenna().calculateDistance(thatPlayer.getRobot())) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        return activationList;
    }

    /**
     * @author Mark
     *
     * Every Element is activated
     */
    private void activationPhase(){
        sendToAllPlayers(new MessageActivePhase(3));

        ArrayList<Player> activationList = generatePlayerActivationList();
        ArrayList<Card> activatedRegisters = new ArrayList<>();
        for(int i = 0; i < 5;i++){

            for (Player player:activationList) {
                activatedRegisters.add(player.getRegister(i));
            }
            //sendToAllPlayers(new MessageCurrentCards());
            for (Card card:activatedRegisters) {
                card.activateCard();
            }
            for (Activatable element:elementRegistry) {
                element.activate();
            }
        }

    }

    /**
     * @author Ringer
     * Stops the game and notyfies the winner
     * @param winner
     */
    public void endGame(Player winner){
        continueGame = false;
        sendToAllPlayers(new MessageGameFinished(winner.getClient().getId()));
    }

    /**
     * @author Ringer
     * @param message
     *
     * sends the Message to all players in playerList
     */

    public void sendToAllPlayers(Message message){
        for (Player player:playerList) {
            player.sendMessage(message);
        }
    }

    /**
     * returns all possible commands to play with
     *
     * @param player
     * @param commandNameString
     * @return LobbyMessage
     */
    /*
    public Message showHelp(ClientHandler player, String commandNameString) {
        System.out.println("Show reference card");
        Message message = new LobbyMessage(0, "/play (card name): play a card \n" +
                "/select (player name): select a player \n" +
                "/guess (card name): guess a players card \n" +
                "/discardpile (player name): view a players discard pile \n" +
                "/discardpile: view all players' discard piles \n" +
                "/reference (card name): view the action of a card \n" +
                "/reference: view the actions of all cards \n" +
                "/score (player name): check how many tokens of affection a player has" +
                "/score: check how many tokens of affection everyone has" +
                "/active: check which players are in the round");
        return message;
    }

     */

    /**
     * returns all players that are active at the moment
     *
     * @return LobbyMessage
     */
    /*
    public Message showActivePlayers() {
        System.out.println("Show active players");


        StringBuilder outputString = new StringBuilder("Active player(s): \n");
        for (Player player : playerList) {
            outputString.append(player.getClient().getClientName() + ", ");
        }
        return new LobbyMessage(0, outputString.toString());
    }
     */

    /**
     * check if the game continue or not
     *
     * @return
     */
    public boolean gameContinues() {
        return true;
    }
    public void winningGame() {
        System.out.println("Game Ends Now");
    }

    /**
     * @author Ringer
     * @return
     */
    public synchronized ArrayList<Player> programmingPlayers(){
        ArrayList<Player> programmingPlayers = new ArrayList<>();
        for (Player otherPlayer:playerList) {
            if(!otherPlayer.isProgramming) programmingPlayers.add(otherPlayer);
        }
        return programmingPlayers;
    }

}

