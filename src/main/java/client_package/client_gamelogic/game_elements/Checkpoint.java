package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
        super(ElementName.CHECKPOINT);
        this.count =jsonObject.get("count").getAsInt();

    }
}

