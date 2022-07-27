package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Empty extends GameElement {


    public Empty(){
        super(ElementName.EMPTY);
        visualOrder = 0;
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Empty (JsonObject jsonObject) throws IOException {
        visualOrder = 0;
        Gson gson = new Gson();
        Empty empty = new Empty();
    }
}
