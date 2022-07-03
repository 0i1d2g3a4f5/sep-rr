package client_package.client_gamelogic.game_elements.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Empty extends GameElement {
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

    public Empty (JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        Empty empty = new Empty();
    }
}
