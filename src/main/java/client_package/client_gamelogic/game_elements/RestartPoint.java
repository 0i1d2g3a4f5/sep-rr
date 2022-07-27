package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class RestartPoint extends GameElement {


    public RestartPoint(){
        super(ElementName.RESTARTPOINT);
        visualOrder = 2;
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public RestartPoint(JsonObject jsonObject) throws IOException {
        this();
        visualOrder = 2;
    }
}
