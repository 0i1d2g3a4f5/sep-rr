package gamelogic.game_elements.robot;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.*;
import gamelogic.cards.CardName;
import gamelogic.game_elements.*;
import gamelogic.map.GameField;
import newmessages.*;
import server_package.Server;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Qinyi, Mark
 * creates a robot character for every player
 */
public class Robot extends GameElement implements RobotMovement, Activatable {

    int activationOrder = 6;

    private boolean interrupted = false;

    private boolean rebootedThisTurn = false;
    private StartPoint startPoint;

    public boolean movedByCBelt = false;
    public boolean waitingForDirection = false;
    private boolean isPlaced = false;

    private Game game;

    private Direction directionFacing;
    private Position position;
    private String name;

    private Direction enteredConveyorBelt = null;

    private Player player;

    private Position nextPosition;
    private Direction nextDirectionFacing;

    public Position getPosition() {
        return position;
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    public void setPosition(Position position) {
        this.position = position;
        gameField = game.getBoard().getField(position);
        Server.serverLogger.info("Position set");
    }


    /**
     * @param position
     * @param direction
     * @author Ringer
     * update the location of the robot
     */
    public Robot (Game game,Position position, Direction direction){
        super(ElementName.ROBOT);

        directionFacing = direction;
        this.position = position;
        this.gameField = game.getBoard().getField(position);
        this.game = game;
        this.orientations.add(direction);

    }
    public Robot (Game game){
        super(ElementName.ROBOT);
        this.game =game;
        directionFacing = Direction.RIGHT;
        this.orientations.add(Direction.RIGHT);

    }
    public void setDirectionFacing(Direction directionFacing) {
        this.directionFacing = directionFacing;
    }


    public StartPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(StartPoint startPoint) {
        this.startPoint = startPoint;
    }

    public void takeDamage(int count) {
        Server.serverLogger.info("Robot "+getPlayer().getClient().getFigure()+" takes damage");
        ArrayList<CardName> damageCards = new ArrayList<>();

        if(game.getSpamDrawPile().size()>=count){
            player.drawSpam(count);
            for (int i = 0; i < count; i++) {

                damageCards.add(CardName.SPAM);

            }
            game.sendToAllPlayers(new MessageDrawDamage(player.getClient().getId(),damageCards));
        }else {
            int remainingToDraw = count-game.getSpamDrawPile().size();
            ArrayList<String> aviablePiles = new ArrayList<>();
            if(game.getTrojanHorseDrawPile().size()>0){
                aviablePiles.add("Trojan");
            }
            if(game.getVirusDrawPile().size()>0){
                aviablePiles.add("Virus");
            }
            if(game.getWormDrawPile().size()>0){
                aviablePiles.add("Worm");
            }
            player.sendMessage(new MessagePickDamage(remainingToDraw,aviablePiles));

        }




    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean left(){
        //if(rebootedThisTurn) return false;
        Direction oldDirection = directionFacing;
        directionFacing = directionFacing.left();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"counterclockwise"));
        Server.serverLogger.info("Left turn");
        Server.serverLogger.debug("Right turn:" + oldDirection +"->"+directionFacing);
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean right(){
        //if(rebootedThisTurn) return false;
        Direction oldDirection = directionFacing;
        directionFacing = directionFacing.right();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        Server.serverLogger.info("Right turn");
        Server.serverLogger.debug("Right turn:" + oldDirection +"->"+directionFacing);
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */

    public boolean uTurn(){
        //if(rebootedThisTurn) return false;
        directionFacing = directionFacing.opposite();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        Server.serverLogger.info("U-Turn");
        return true;
    }

    public void interrupt(){

    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean forward(int distance){
        //if(rebootedThisTurn) return false;
        Position oldPos = position.clone();
        boolean success = true;

        for (int i = 0;i<distance;i++){
            if(interrupted)return false;
            if(!move(1)){

                success = false;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getY(), position.getX()));
        interrupted = false;
        return success;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean backward(int distance){
        //if(rebootedThisTurn) return false;
        Position oldPos = position.clone();
        boolean success = true;
        for (int i = 0;i<distance;i++){
            if(!move(-1)){
                success = false;
            }
        }
        //game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getX() - oldPos.getX(), position.getY() - oldPos.getY()));
        return success;
    }

    /**
     * @auther Ringer
     * @param gear is The Direction
     * @return
     */
    private boolean move(int gear){
        Direction targetDirection = gear == 1 ? directionFacing : directionFacing.opposite();
        setNextPosition(targetDirection);
        Server.serverLogger.info("Robot wants to move from " + position + " to " + nextPosition);

        if(checkNextPosition(targetDirection)){
            changePositionOnBoard();
            Server.serverLogger.info("Robot successfully moved to " + position);

        }else{
        Server.serverLogger.info("Path is blocked, turn ends");
        player.clearAllRegister();
    }



        return true;
    }

    public Direction getEnteredConveyorBelt() {
        return enteredConveyorBelt;
    }

    public void setEnteredConveyorBelt(Direction enteredConveyorBelt) {
        this.enteredConveyorBelt = enteredConveyorBelt;
    }

    /**
     * @author Ringer
     * @param targetDirection
     * @return
     */
    public boolean displace(Direction targetDirection){
        setNextPosition(targetDirection);

        if(checkNextPosition(targetDirection))
            changePositionOnBoard();

        return true;
    }

    /**
     * @author Ringer
     * @return
     */
    private boolean changePositionOnBoard() {

        Game game = Game.getInstance();
        GameField currentField = gameField;
        GameField nextField = game.board.getField(nextPosition);

        if(!nextField.addRobot(this)) return false;
        currentField.removeRobot();
        if(currentField.getElements().size()==0){
            Empty empty = new Empty();
            empty.setGameField(currentField);
            empty.setIsOnBoard(currentField.getIsOnBoard());
            currentField.addElement(empty);

        }

        position = nextPosition;
        nextPosition = null;
        gameField.removeRobot();
        gameField = nextField;
        gameField.addRobot(this);
        Server.serverLogger.debug("Robot "+this.getPlayer().getClient().getFigure()+" is placed on GameField "+gameField.toString());


        player.getClient().sendAll(new MessageMovement(player.getClient().getId(), position.getY(), position.getX()));

        return true;
    }

    /**
     * @author Ringer
     * @param targetDirection
     * @return
     */
    private boolean checkNextPosition(Direction targetDirection) {

        Game game = Game.getInstance();
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        if (currentField.checkWall(targetDirection) || nextField.checkWall(targetDirection.opposite())){
            Server.serverLogger.info("Robot "+getPlayer().getClient().getFigure() +"buped into a Wall");
            return false;
        }
        if(nextField.contains(ElementName.ANTENNA)) {
            Server.serverLogger.info("Robot " + getPlayer().getClient().getFigure() + " Bumped into the Antenna");
            return false;
        }


        if(nextField.contains(ElementName.PIT)||nextField==null){
            Server.serverLogger.info("Robot "+getPlayer().getClient().getFigure() +"fell into a Pit -> Reboot");
            reboot();
            return false;
        }
        if(nextField.contains(ElementName.ROBOT)){
            Server.serverLogger.info("Blocking Robot");
            Robot blockingRobot = nextField.getRobot();
            if(blockingRobot.displace(directionFacing)){
                Server.serverLogger.debug("displaced Robot because it was blocking");
                return true;

            }else {
                return false;
            }
        }



        return true;
    }

    public boolean isRebootedThisTurn() {
        return rebootedThisTurn;
    }

    public void setRebootedThisTurn(boolean rebootedThisTurn) {
        this.rebootedThisTurn = rebootedThisTurn;
    }

    /**
     * @uthor Ringer
     */
    public void reboot() {
        Server.serverLogger.info("rebooting "+ this);
        Server.serverLogger.debug("Reboot points: "+game.board.restartPoints);
        takeDamage(2);
        player.discardAllHandCards();
        player.clearAllRegister();
        rebootedThisTurn = true;
        gameField.removeRobot();
        game.sendToAllPlayers(new MessageReboot(player.getClient().getId()));
        waitingForDirection = true;
        interrupt();
        finishReboot(Direction.TOP);




        //TODO case not answered

    }
    private void laserMovement(GameField gameField){
        if(gameField != null){
            if(gameField.contains(ElementName.ROBOT)){
                Server.serverLogger.info("Laser hits Robot "+gameField.getRobot());
                Robot robot = (Robot) gameField.getElement(ElementName.ROBOT);
                robot.takeDamage(1);
            }else{
                GameField nextField = gameField.getNeighbor(directionFacing);
                if(nextField.getPosition().getY() <0 || nextField.getPosition().getY()>= Game.getInstance().board.getBoardMap().get(0).size() ||nextField.getPosition().getX() <0 || nextField.getPosition().getX()>= Game.getInstance().board.getBoardMap().size())
                    return;
                else if(!nextField.contains(ElementName.ANTENNA)&&!gameField.checkWall(directionFacing)&&!nextField.checkWall(directionFacing.opposite())) laserMovement(nextField);
            }
        }
    }


    /**
     * @author Mark Ringer
     */
    public void finishReboot(Direction direction){

        isOnBoard = gameField.getIsOnBoard();
        Server.serverLogger.debug("Robot "+getPlayer().getClient().getFigure()+ " IsOnBoard: "+isOnBoard);
        //TODO case field blocked
        GameElement restartingPoint= null;
        for (RestartPoint restartPoint:game.getBoard().restartPoints) {
            if(isOnBoard.equals(restartPoint.getIsOnBoard())){
                restartingPoint = restartPoint;

            }
        }
        if(restartingPoint==null){
            restartingPoint =startPoint;
        }
        if(restartingPoint.getGameField().contains(ElementName.ROBOT)){
            if(restartingPoint instanceof RestartPoint){
                if(restartingPoint.orientations.size()>0)
                    restartingPoint.getGameField().getRobot().displace(restartingPoint.orientations.get(0));
                else
                    restartingPoint.getGameField().getRobot().displace(restartingPoint.getGameField().getRobot().directionFacing);
            } else {
                restartingPoint.getGameField().getRobot().displace(restartingPoint.getGameField().getRobot().directionFacing);
            }
        }

        position=restartingPoint.getGameField().getPosition();
        gameField.removeRobot();
        if(gameField.getElements().size()==0){
            Empty empty = new Empty();
            empty.setGameField(gameField);
            empty.setIsOnBoard(gameField.getIsOnBoard());
            gameField.addElement(empty);
        }
        gameField=restartingPoint.getGameField();
        while(directionFacing != direction){
            right();
        }
        game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getY(), position.getX()));
    }



    /**
     * @author Ringer
     * set the next Position if a robot meet the gear
     * @param targetDirection
     * @return
     */
    private boolean setNextPosition(Direction targetDirection) {
        switch (targetDirection){

            case TOP -> nextPosition = new Position(position.getY()-1, position.getX());
            case BOTTOM -> nextPosition = new Position(position.getY()+1, position.getX());
            case RIGHT -> nextPosition = new Position(position.getY(), position.getX()+1);

            case LEFT -> nextPosition = new Position(position.getY(), position.getX()-1);

            default -> {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the name of the robot
     */
    public String getName(){
        return name;
    }

    @Override
    public JsonObject toJson() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));
        jsonObject.add("orientations",gson.toJsonTree(orientations.toString()));
        return jsonObject;

    }

    public String toString(){
        return String.valueOf(this.getPlayer().getClient().getFigure());
    }
    /**
     * @return the max Lives of a robot
     */
    public int getMaxLives(){
        return 3;
    }


    @Override
    public void activate() {

        System.out.println("activate "+type);
        laserMovement(gameField.getNeighbor(directionFacing));
        //TODO activate Robot Lasers
    }

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }
}
