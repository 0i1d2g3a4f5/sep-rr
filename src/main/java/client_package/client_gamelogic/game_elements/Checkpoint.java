package client_package.client_gamelogic.game_elements;

import client_package.Client;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.map.GameField;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Position;

import java.io.IOException;

/**
 * @author Mark Ringer
 */
public class Checkpoint extends GameElement {

    int count;

    /**
     * Constructor
     *
     * @param count
     * @author Mark Ringer
     */
    public Checkpoint(int count){
        super(ElementName.CHECKPOINT);
        visualOrder = 3;
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    /**
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public Checkpoint(JsonObject jsonObject) throws IOException {
        super(ElementName.CHECKPOINT);
        visualOrder = 3;
        this.count =jsonObject.get("count").getAsInt();
    }

    /**
     * moves Checkpoint to the coordinates y|x
     *
     *
     * @param y
     * @param x
     * @throws IOException
     *
     * @author Mark Ringer
     */
    public synchronized void moveCheckpointTo(int y, int x) throws IOException {
        gameField.removeElement(this);
        GameField oldField = gameField;
        oldField.removeAll(ElementName.CHECKPOINT);
        Game.getInstance().getMap().getGameField(y,x).addElement(this);
        gameField = Game.getInstance().getMap().getGameField(y,x);
        Client.clientLogger.debug("Moved Checkpoint from " + oldField + " to "+gameField);
    }

}

