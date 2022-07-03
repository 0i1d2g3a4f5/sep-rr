package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class EnergyCube extends GameElement {
    public EnergyCube(){

    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public EnergyCube(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        EnergyCube energyCube = new EnergyCube();
    }

}
