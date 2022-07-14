package gamelogic.game_elements.robot;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.*;
import gamelogic.cards.CardName;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;
import gamelogic.game_elements.RestartPoint;
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

    public boolean movedByCBelt = false;

    private boolean isPlaced = false;

    private Game game;

    private Direction directionFacing;
    private Position position;
    private String name;

    private boolean isAlive;

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

        directionFacing = direction;
        this.position = position;
        this.gameField = game.getBoard().getField(position);
        this.game = game;
        this.orientations.add(direction);

    }
    public Robot (Game game){
        this.game =game;
        directionFacing = Direction.EAST;
        this.orientations.add(Direction.EAST);

    }

    public void takeDamage(int count) {
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
        directionFacing = directionFacing.opposite();
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        game.sendToAllPlayers(new MessagePlayerTurning(player.getClient().getId(),"clockwise"));
        Server.serverLogger.info("U-Turn");
        return true;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean forward(int distance){
        Position oldPos = position.clone();
        boolean success = true;

        for (int i = 0;i<distance;i++){
            if(!move(1)){

                success = false;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        game.sendToAllPlayers(new MessageMovement(player.getClient().getId(), position.getX(), position.getY()));
        return success;
    }

    /**
     * @author Ringer
     * @param distance
     * @return
     */
    public boolean backward(int distance){
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

        }


        return true;
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
        GameField currentField = game.board.getField(position);
        GameField nextField = game.board.getField(nextPosition);

        if(!nextField.addRobot(this)) return false;
        currentField.removeRobot();
        position = nextPosition;
        nextPosition = null;
        gameField = game.board.getField(position);

        player.getClient().sendAll(new MessageMovement(player.getClient().getId(),position.getX(),position.getY()));

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

        if(nextField.contains(ElementName.PIT)||nextField==null){
            Server.serverLogger.info("Robot "+getPlayer().getClient().getFigure() +"fell into a Pit -> Reboot");
            reboot();
            return false;
        }


        return true;
    }

    /**
     * @uthor Ringer
     */
    public void reboot() {
        takeDamage(2);
        player.discardAllHandCards();
        player.clearAllRegister();

        game.sendToAllPlayers(new MessageReboot(player.getClient().getId()));
        //TODO case not answered

    }


    /**
     * @author Mark Ringer
     */
    public void finishReboot(Direction direction){
        //TODO case field blocked
        for(RestartPoint restartPoint:game.board.restartPoints){
            if(restartPoint.getIsOnBoard()==isOnBoard){
                getGameField().removeRobot();
                restartPoint.getGameField().addRobot(this);
                gameField = restartPoint.getGameField();
                position = gameField.getPosition();
                directionFacing = direction;
                player.getClient().sendAll(new MessageMovement(player.getClient().getId(),position.getX(),position.getY()));

            }
        }
    }



    /**
     * @author Ringer
     * set the next Position if a robot meet the gear
     * @param targetDirection
     * @return
     */
    private boolean setNextPosition(Direction targetDirection) {
        switch (targetDirection){

            case NORTH -> nextPosition = new Position(position.getY()+1, position.getX());
            case SOUTH -> nextPosition = new Position(position.getY()-1, position.getX());
            case EAST -> nextPosition = new Position(position.getY(), position.getX()+1);

            case WEST -> nextPosition = new Position(position.getY(), position.getX()-1);

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
        return this.name;
    }
    /**
     * @return the max Lives of a robot
     */
    public int getMaxLives(){
        return 3;
    }


    @Override
    public void activate() {
        //TODO (Vivian): Mark, do we keep this in?
        System.out.println("activate "+type);
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
