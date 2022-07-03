package client_package.client_gamelogic.game_elements.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;

public class StartPoint extends GameElement {
    public StartPoint(){
        super(ElementName.STARTPOINT);
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public StartPoint(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        StartPoint startPoint = new StartPoint();
    }


}
