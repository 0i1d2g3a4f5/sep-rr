package client_package.sentient;

import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.game_elements.Checkpoint;
import client_package.client_gamelogic.game_elements.ElementName;
import client_package.client_gamelogic.game_elements.GameElement;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;
import gamelogic.Direction;
import gamelogic.Position;
import messages.*;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sarp Cagin Erdogan
 */
public class SentientBehaviour {
    SentientClient sentientClient;
    boolean canChooseNext, allChosen, directionHandled, triedToHandle, canMoveForward;
    public SentientBehaviour(SentientClient sentientClient){
        this.sentientClient = sentientClient;
        canChooseNext = false;
        allChosen = false;
        directionHandled=false;
        triedToHandle=false;
        canMoveForward=true;
    }

    /**
     * starts manual AI
     *
     * @param name
     * @param group
     */
    public void start(String name, String group) {
        sentientClient.setGroup(group);
        int i = 1+ (int) ( Math.random() * 9999);
        String str = String.valueOf(i);
        sentientClient.setName(name+str);
        createSocket();
        sentientClient.listen();
    }

    /**
     * starts automatic AI
     *
     * @param name
     * @param string
     * @param port
     * @param group
     */
    public void start(String name, String string, int port, String group){
        int i = 1+ (int) ( Math.random() * 9999);
        String str = String.valueOf(i);
        sentientClient.setName(name+str);
        createSocket(string, port);
        sentientClient.listen();
        sentientClient.setGroup(group);
    }

    /**
     * creates Socket connection to server (server ip and port will be given while processing)
     *
     * @return
     */
    public boolean createSocket(){
        boolean result = false;
        String ip = scanIP();
        int port = scanPort();
        try {
            sentientClient.setSocket(new Socket(ip, port));
            result=true;
        } catch (UnknownHostException e) {
            sentientClient.getLogger().error("Couldn't create socket. Please retry.");
            createSocket();
            throw new RuntimeException(e);
        } catch (IOException e) {
            sentientClient.getLogger().error("Couldn't create socket. Please retry.");
            createSocket();
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * creates Socket connection to given sever (automatic)
     *
     * @return
     */
    public boolean createSocket(String ip, int port){
        boolean result = false;
        try {
            sentientClient.setSocket(new Socket(ip, port));
            result=true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * asks for ip adress of server to connect with in command line
     *
     * @return
     */
    public String scanIP(){
        System.out.println("Please provide ip: ");
        Scanner scanner = new Scanner(System.in);
        String scannedIP = scanner.next();
        return scannedIP;
    }

    /**
     * asks for port of server to connect with in command line
     *
     * @return
     */
    public int scanPort(){
        System.out.println("Please provide port: ");
        Scanner scanner = new Scanner(System.in);
        String scannedPort = scanner.next();
        int port = 0;
        try {
            port = Integer.valueOf(scannedPort);
        } catch (NumberFormatException numberFormatException){
            System.out.println("Port should be a number.");
            return scanPort();
        }
        return port;
    }

    /**
     * initializes checkpoint positions of all checkpoints in game
     */
    public void initializeCheckPoints(){
        while (sentientClient.getGame().getMap()==null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int x = 0; x<sentientClient.getGame().getMap().getDimensionX(); x++){
            for(int y = 0; y<sentientClient.getGame().getMap().getDimensionY(); y++){
                for(GameElement gameElement : sentientClient.getGame().getMap().getGameField(y, x).getElements()){
                    if(gameElement.getType().equals(ElementName.CHECKPOINT)){
                        Checkpoint checkpoint = (Checkpoint) gameElement;
                        sentientClient.nextCheckPoints.put(checkpoint.getCount(), new Position(y, x));
                    }
                }
            }
        }
    }

    /**
     * increase number of next checkpoint to reach with 1 after reaching a checkpoint
     */
    public void nextCheckPoint(){
        sentientClient.currentGoal++;
    }

    public void sayHelloToServer(){
        sentientClient.sendSelf(new MessageHelloServer(sentientClient.getGroup(), true, sentientClient.getProtocolVersion()));
    }

    public void sendOwnInfo(){
        sentientClient.sendSelf(new MessagePlayerValues(sentientClient.getName(), sentientClient.lastTriedFigure));
    }

    public void retryFigure(){
        if(sentientClient.lastTriedFigure<7){
            sentientClient.lastTriedFigure++;
            sendOwnInfo();
        }
        else{
            sentientClient.getLogger().error("No figures available :(");
        }
    }

    /**
     * triggers game start and loads map
     * @param jsonObject
     * @throws IOException
     */
    public void triggerGameStart(JsonObject jsonObject) throws IOException {
        Game game = Game.getInstance();
        game.setMap(new GameBoard(jsonObject));
        sentientClient.setGame(game);
        sentientClient.initializeStartingPoints();
        initializeCheckPoints();
    }

    /**
     * chooses Cards to play with
     */
    public void chooseAllCards(){
        allChosen=sentientClient.getPlayer().registerFull();
        directionHandled=false;
        triedToHandle=false;
        while(!allChosen){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!allChosen && canChooseNext){
                canChooseNext=false;
                System.out.println("choosing next");
                if(!triedToHandle && !directionHandled){
                    System.out.println("trying to handle direction");
                    handleDirection();
                }
                else{
                    System.out.println("trying to not handle direction");
                    if(directionHandled && canMoveForward){
                        System.out.println("trying to move forward");
                        moveForward();
                    }
                    else{
                        System.out.println("trying to choose random");
                        chooseCardRandom();
                    }
                }
            }
        }
    }

    /**
     * chooses random card from available cards
     */
    public void chooseCardRandom(){
        ArrayList<Integer> chooseFrom = sentientClient.getPlayer().availableIndexes();
        int randomIndex = (int) ( Math.random() * chooseFrom.size());
        int selectedIndex = chooseFrom.get(randomIndex);
        int emptyRegister = sentientClient.getPlayer().selectCardOwn(selectedIndex);
        sentientClient.sendSelf(new MessageSelectedCard(sentientClient.getPlayer().getOwnSelectedCard().getValue().toString(), emptyRegister));
    }

    /**
     * checks distance to next checkpoint (x and y) and handles lower one
     */
    public void handleDirection(){
        Position currentPos = sentientClient.getPlayer().getRobot().getPosition();
        Position currentGoal = sentientClient.nextCheckPoints.get(sentientClient.currentGoal);
        int xdiff = currentGoal.getX()-currentPos.getX();
        int ydiff = currentGoal.getY()-currentPos.getY();
        if(Math.abs(xdiff)>Math.abs(ydiff)){
            handleXDiff(xdiff>0);
        }
        else{
            handleYDiff(ydiff>0);
        }
    }

    /**
     * turns robot to x direction with shortest way to next checkpoint
     * @param positive
     */
    public void handleXDiff(boolean positive){
        Direction direction = sentientClient.getPlayer().getRobot().getDirectionFacing();
        if(positive){

            if(direction.equals(Direction.LEFT)){
                tryToTurn(CardName.UTURN);
            }
            else if(direction.equals(Direction.TOP)){
                tryToTurn(CardName.TURNRIGHT);
            }
            else if(direction.equals(Direction.BOTTOM)){
                tryToTurn(CardName.TURNLEFT);
            }else{
                directionHandled=true;
                canChooseNext=true;
            }
        }
        else{
            if(direction.equals(Direction.RIGHT)){
                tryToTurn(CardName.UTURN);
            }
            else if(direction.equals(Direction.BOTTOM)){
                tryToTurn(CardName.TURNRIGHT);
            }
            else if(direction.equals(Direction.TOP)){
                tryToTurn(CardName.TURNLEFT);
            }else{
                directionHandled=true;
                canChooseNext=true;
            }
        }
    }

    /**
     * turns robot to y direction with shortest way to next checkpoint
     * @param positive
     */
    public void handleYDiff(boolean positive){
        Direction direction = sentientClient.getPlayer().getRobot().getDirectionFacing();
            if(positive){
                if(direction.equals(Direction.BOTTOM)){
                    tryToTurn(CardName.UTURN);
                }
                else if(direction.equals(Direction.LEFT)){
                    tryToTurn(CardName.TURNRIGHT);
                }
                else if(direction.equals(Direction.RIGHT)){
                    tryToTurn(CardName.TURNLEFT);
                }else{
                    directionHandled=true;
                    canChooseNext=true;
                }
            }
            else {
                if(direction.equals(Direction.TOP)){
                    tryToTurn(CardName.UTURN);
                }
                else if(direction.equals(Direction.RIGHT)){
                    tryToTurn(CardName.TURNRIGHT);
                }
                else if(direction.equals(Direction.LEFT)){
                    tryToTurn(CardName.TURNLEFT);
                }else{
                    directionHandled=true;
                    canChooseNext=true;
                }
            }
        }

    /**
     * tries to find needed turning card in available cards
     *
     * @param cardName
     */
    public void tryToTurn(CardName cardName){
        for(int i = 0; i<sentientClient.getPlayer().getAvailableCardsOwn().size(); i++){
            if(sentientClient.getPlayer().getAvailableCardsOwn().get(i) != null){
                if(sentientClient.getPlayer().getAvailableCardsOwn().get(i).getCardName().equals(cardName)){
                    int emptyRegister = sentientClient.getPlayer().selectCardOwn(i);
                    sentientClient.sendSelf(new MessageSelectedCard(sentientClient.getPlayer().getOwnSelectedCard().getValue().toString(), emptyRegister));
                    directionHandled=true;
                    return;
                }
            }
        }
        canChooseNext=true;
        triedToHandle=true;
    }

    /**
     * tries to find move cards in available cards to move forward
     */
    public void moveForward(){
        for(int i = 0; i<sentientClient.getPlayer().getAvailableCardsOwn().size(); i++){
            if(sentientClient.getPlayer().getAvailableCardsOwn().get(i) != null){
                if(sentientClient.getPlayer().getAvailableCardsOwn().get(i).getCardName().equals(CardName.MOVEI) || sentientClient.getPlayer().getAvailableCardsOwn().get(i).getCardName().equals(CardName.MOVEII) || sentientClient.getPlayer().getAvailableCardsOwn().get(i).getCardName().equals(CardName.MOVEIII)){
                    int emptyRegister = sentientClient.getPlayer().selectCardOwn(i);
                    sentientClient.sendSelf(new MessageSelectedCard(sentientClient.getPlayer().getOwnSelectedCard().getValue().toString(), emptyRegister));
                    return;
                }
            }
        }
        canChooseNext=true;
        canMoveForward=false;
    }

    public void chooseRebootDirection()
    {
        chooseRandomRebootDirection();
    }

    /**
     * chooses random reboot direction
     */
    public void chooseRandomRebootDirection(){
        int randomIndex = (int) ( Math.random() * 4);
        Direction direction;
        switch (randomIndex){
            case 0 -> {
                direction=Direction.RIGHT;
            }
            case 1 -> {
                direction=Direction.LEFT;
            }
            case 2 -> {
                direction=Direction.BOTTOM;
            }
            case 3 -> {
                direction=Direction.TOP;
            }
            default -> {
                direction=Direction.RIGHT;
            }
        }
        sentientClient.sendSelf(new MessageRebootDirection(direction.toString()));

    }

    /**
     * gives AI the right to choose the next card in a register
     * @param canChooseNext
     */
    public void setCanChooseNext(boolean canChooseNext) {
        this.canChooseNext = canChooseNext;
    }

    /**
     * informs AI that all card are chosen
     * @param allChosen
     */
    public void setAllChosen(boolean allChosen) {
        this.allChosen = allChosen;
    }
}
