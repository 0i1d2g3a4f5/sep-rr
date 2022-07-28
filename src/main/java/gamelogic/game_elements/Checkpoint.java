package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.*;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameField;
import javafx.geometry.Pos;

import messages.MessageCheckPointMoved;
import messages.MessageMovement;
import server_package.Server;
import utility.SearchMethods;

import java.io.IOException;

public class Checkpoint extends GameElement implements Activatable {


    public boolean movedByCBelt = false;
    int activationOrder = 8;
    public static int numberOfCheckpoints;
    int count;
    public Checkpoint(int count){
        super(ElementName.CHECKPOINT);
        this.count =count;
        numberOfCheckpoints++;
    }

    public int getCount(){
        return count;
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Checkpoint(JsonObject jsonObject) throws IOException {
        super(ElementName.CHECKPOINT);
        Gson gson = new Gson();

        count = jsonObject.get("count").getAsInt();
        isOnBoard = jsonObject.get("isOnBoard").getAsString();
    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */
@Override
    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }

    /**
     * @author Ringer
     *
     *gets the Robot above and adds a Token to its players inventory.
     * if this is the last checkpoint, the Game ends and the CPlayer wins
     */
    @Override
    public void activate() {
        Robot robot= (Robot) SearchMethods.searchElement(ElementName.ROBOT,getGameField().getElements());

        if(robot !=null){

            Player player = robot.getPlayer();
            if(player.getCheckpointTokens()==count-1){
                player.addCheckpointToken();
                if(player.getCheckpointTokens()>=numberOfCheckpoints){
                    Game.getInstance().endGame(player);
                }
            } else{
                Server.serverLogger.info("Wrong order of checkpoints: This is checkpoint " + count + ";\n" +
                        "try to reach checkpoint " + player.getCheckpointTokens() + 1);
            }


        }

    }

    public boolean displace(Direction targetDirection){
        Position oldPos = position;
        GameField oldField =gameField;
        GameField nextField = gameField.getNeighbor(targetDirection);
        position = nextField.getPosition();
        nextField.addElement(this);
        oldField.removeAll(ElementName.CHECKPOINT);
        if(gameField.getElements().size()==0){
            Empty empty = new Empty();
            empty.setGameField(gameField);
            empty.setIsOnBoard(gameField.getIsOnBoard());
            gameField.addElement(empty);

        }
        gameField = nextField;
        Game.getInstance().getPlayerList().get(0).getClient().sendAll(new MessageCheckPointMoved(count, position.getY(), position.getX()));
        Server.serverLogger.debug("Moved Checkpoint "+count+ " from "+oldPos+" to "+position+". Old Gamefield: "+oldField );

        return true;
    }

    @Override
    public int getActivationOrder() {
        return activationOrder;
    }


    /**
     * @author Ringer
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.

     */
    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }
}
