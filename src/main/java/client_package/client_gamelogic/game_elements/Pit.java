package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Pit extends GameElement {

    public Pit(){
        
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Pit(JsonObject jsonObject) throws IOException {
        super(ElementName.PIT);
        Gson gson = new Gson();
        Pit pit = new Pit();
    }


}
