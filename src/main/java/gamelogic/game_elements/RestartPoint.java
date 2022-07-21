package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class RestartPoint extends GameElement{


    public RestartPoint(){
        super(ElementName.RESTARTPOINT);
    }
    public RestartPoint(Direction direction){
        super(ElementName.RESTARTPOINT);
        orientations.add(direction);
    }


    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public RestartPoint(JsonObject jsonObject) throws IOException {
        super(ElementName.RESTARTPOINT);
        Gson gson = new Gson();
        if(jsonObject.has("orientations")){
            JsonArray array = jsonObject.get("orientations").getAsJsonArray();
            orientations.add(Direction.parseDirection(array.get(0).getAsString()));
        }

        else
            orientations.add(Direction.TOP);

        isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
        if(orientations.size()>0){
            JsonArray array = new JsonArray();
            array.add(orientations.get(0).toString());
            jsonObject.add("orientations",array);
        }

        return jsonObject;
    }


}
