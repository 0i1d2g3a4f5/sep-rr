package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class StartPoint extends GameElement {
    public StartPoint(){
        super(ElementName.STARTPOINT);
        visualOrder = 2;
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public StartPoint(JsonObject jsonObject) throws IOException {

        this();
        visualOrder = 2;
    }


}
