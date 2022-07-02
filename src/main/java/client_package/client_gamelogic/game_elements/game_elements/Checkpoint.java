package client_package.client_gamelogic.game_elements.game_elements;

import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Game;
import gamelogic.Player;
import utility.SearchMethods;

import java.io.IOException;

public class Checkpoint extends GameElement {


    int count;
    public Checkpoint(int count){
        super(ElementName.CHECKPOINT);
        this.count =count;
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
        Gson gson = new Gson();

        Checkpoint checkpoint = new Checkpoint(jsonObject.get("count").getAsInt());
    }
}

