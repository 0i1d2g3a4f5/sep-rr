package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;

public class EnergyCube extends GameElement{
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
        super(ElementName.ENERGY_CUBE);
        Gson gson = new Gson();

        EnergyCube energyCube = new EnergyCube();
        energyCube.isOnBoard = jsonObject.get("isOnBoard").getAsString();

    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */
    @Override
    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));

        return jsonObject;
    }

}
