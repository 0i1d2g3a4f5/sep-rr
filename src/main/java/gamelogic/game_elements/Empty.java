package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Direction;

import java.io.IOException;

public class Empty extends GameElement{
    public Empty(){
        super(ElementName.EMPTY);
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public static Empty fromJson(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        Empty empty = new Empty();
        empty.isOnBoard = jsonObject.get("isOnBoard").getAsString();

        return empty;
    }
}
