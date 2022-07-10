package gamelogic;


import gamelogic.cards.CardName;
import gamelogic.game_elements.ElementName;
import server_package.SClient;
import gamelogic.cards.Card;
import gamelogic.cards.DeckSerializer;
import gamelogic.cards.damage_card.*;
import gamelogic.cards.damage_card.TrojanHorse;
import gamelogic.map.MapName;
import gamelogic.map.GameBoard;
import gamelogic.map.ModelLoader;
import newmessages.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Mark Ringer
 * Class serves as the main acces to the Gamelogic.
 * It's a Singleton.
 */
public class  Game {

    public Object lock = new Object();
    private int activeRegister = -1;
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
    public ArrayList<Player> playerList = new ArrayList<>();
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
    public ArrayList<Player> getPlayerList(){
        return playerList;
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
    public int getActiveRegister() {
        return activeRegister;
    }

    /**
     * players join the game
     * @return
     */
    public boolean isProgramingPhase() {
        return programmingPhase;
    }
    public synchronized void setProgrammingPhase(boolean programmingPhase) {
        this.programmingPhase = programmingPhase;
    }
    public GameBoard board;
    public GameBoard getBoard(){
        return board;
    }
    public void setBoard(GameBoard gameBoard){
        this.board=gameBoard;
    }

    /**
     * @author Mark Ringer
     * player can join the Game
     * @param sClient
     * @return
     */
    public Player join(SClient sClient) {
        //TODO get Robot from player
        Player player = new Player(sClient,this);
        sClient.setPlayer(player);
        for (Player existingPlayer : playerList) {
            if (existingPlayer.getClient().getId() == sClient.getId()) {
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
    public void setup(MapName mapName) throws IOException, InterruptedException {
        System.out.println("Game Setup");
        this.mapName = mapName;
        sendToAllPlayers(new MessageActivePhase(0));
        //DeckSerializer deckSerializer = new DeckSerializer();
        //TODO initialize player decks
        //select map
        System.out.println("ModelLoader");
        ModelLoader loader = new ModelLoader();
        try {
            //TODO allow different maps
            System.out.println("load Map");
            board = loader.loadMap(String.valueOf(mapName));
            System.out.println("Map: "+board.boardMap);
            System.out.println("loaded Map");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Map: "+board.boardMap);
        elementRegistry = board.getRegistry();

        //TODO special cards

        //Setup Spam
        spamDrawPile = new Stack<Spam>();
        for(int i = 0; i < 38; i++)
            spamDrawPile.add(new Spam());
        //Setup Virus
        virusDrawPile = new Stack<Virus>();
        for(int i = 0; i < 18; i++)
            virusDrawPile.add(new Virus());
        //Setup TrojanHorse
        trojanHorseDrawPile = new Stack<TrojanHorse>();
        for(int i = 0; i < 12; i++)
            trojanHorseDrawPile.add(new TrojanHorse());
        //Setup Worm
        wormDrawPile = new Stack<Worm>();
        for(int i = 0; i<6; i++)
            wormDrawPile.add(new Worm());

        //setup upgradeWarehouse
        //TODO upgrades
        //upgradeWarehouse = deckSerializer.builtDeck(mapName.toString());

        //setup upgradeShop
        /*
        upgradeShop = new ArrayList<Card>();
        for(int i = 0;i< playerList.size();i++){
            upgradeShop.add(upgradeWarehouse.pop());
        }

         */


        //TODO place Robot
    }

    /**
     * @author Ringer
     * @param player
     * @param position
     * @return
     */
    public synchronized boolean placeRobot(Player player,Position position){
        if(board.getField(position).contains(ElementName.ROBOT)) return false;
        else if(board.getField(position).contains(ElementName.STARTPOINT)){
            board.getField(position).addRobot(player.getRobot());
            robotsPlaced++;
            player.getRobot().setPlaced(true);
            player.getRobot().setPosition(position);
            return true;
        } else return false;
    }

    /**
     * @author Ringer
     * @throws IOException
     * @throws InterruptedException
     */
    public void startGame() throws IOException, InterruptedException {
        System.out.println("Game started");
        sendToAllPlayers(new MessageActivePhase(2));
        continueGame=true;
        gameLoop();

    }

    /**
     * @author Ringer
     * @throws InterruptedException
     */
    public void gameLoop() throws InterruptedException {
        while (continueGame){

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
     *
     */
    private void drawCards(){
        for (Player player:playerList) {
            player.drawCards();
            ArrayList<Card> handCards= player.getHandCards();
            CardName[] cardNames = new CardName[handCards.size()];

            for (int i = 0; i < cardNames.length; i++) {
                cardNames[i] = handCards.get(i).getCardName();
            }

            for (Player otherPlayer:playerList) {
                if(player.getClient().getId()!= otherPlayer.getClient().getId())
                    player.sendMessage(new MessageNotYourCards(player.getClient().getId(), cardNames.length));
            }
            player.sendMessage(new MessageYourCards(player.getClient().getId(),cardNames));
        }
    }

    /**
     * @author Ringer
     * Draw cards and arrange them
     */
    private void programmingPhase() throws InterruptedException {
        System.out.println("Phase: ProgrammingPhase");

        programmingPhase =true;
        drawCards();

        for (Player player:playerList) {
            System.out.println("send to:" + player.getClient().getName());
            player.getClient().sendSelf(new MessageActivePhase(2));
            player.isProgramming = true;
        }
        while(programmingPhase){

            TimeUnit.SECONDS.sleep(1);
            //System.out.println("GameLoop still alive- programmingPhase: "+ programmingPhase);


        }
        System.out.println("Ending Programming phase");
        endProgrammingPhase();
    }

    /**
     * @author Mark Ringer
     * Informs all players, that the Timer has started and force-ends their programming Phase
     * @throws InterruptedException
     */
    public void endProgrammingPhase()  {

        sendToAllPlayers(new MessageTimerStarted());
        try {
            for (int i = 30; i>0; i--) {
                if(programmingPlayers().size()<=0)
                    break;
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Player> programmingPlayers = programmingPlayers();
        sendToAllPlayers(new MessageTimerEnded(programmingPlayers));

        for (Player player:programmingPlayers) {
            player.discardAllHandCards();

            Card[] register = player.getAllRegisters();
            //TODO Probably buggy
            ArrayList<Card> drawnCards = new ArrayList<>();
            for (int i = 0; i < register.length; i++) {
                if(register[i]==null) {
                    Card drawnCard = player.drawCard();
                    drawnCards.add(drawnCard);
                    register[i] = drawnCard;
                }
            }
            player.sendMessage(new MessageCardsYouGotNow(drawnCards));

        }


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

        ArrayList<Player> activationList;
        ArrayList<Card> activatedRegisters = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            activeRegister = i;
            activationList = generatePlayerActivationList();
            for (Player player:activationList) {
                Card card = player.getRegister(i);
                if(card != null)
                    activatedRegisters.add(card);
                else System.out.println("Card on pos "+ i+ " is null");

            }
            sendToAllPlayers(new MessageCurrentCards(activatedRegisters,activationList));
            for (Card card:activatedRegisters) {
                card.activateCard();
            }
            careEnergyCube();

            for (Activatable element:elementRegistry) {
                element.activate();
            }
        }
        activeRegister = -1;
        for (Player player:playerList) {
            player.clearAllRegister();
            player.discardAllHandCards();
        }
    }

    private void careEnergyCube(){
        for(Player player:playerList){
            if(player.getRobot().getGameField().contains(ElementName.ENERGY_CUBE)){
                player.addEnergyCube();
                player.getRobot().getGameField().removeEnergyCube();
                player.getClient().sendAll(new MessageEnergy(player.getClient().getId(),1,"EnergyCube"));

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
        //TODO javafx action
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
        for (CPlayer player : playerList) {
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


