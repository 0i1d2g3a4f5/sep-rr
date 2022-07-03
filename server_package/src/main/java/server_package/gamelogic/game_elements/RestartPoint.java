package server_package.gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;

public class RestartPoint extends GameElement{


    public RestartPoint(){
        super(ElementName.RESTARTPOINT);
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public RestartPoint(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        RestartPoint restartPoint = new RestartPoint();
        restartPoint.isOnBoard = jsonObject.get("isOnBoard").getAsString();


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
