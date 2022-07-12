package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class StartPoint extends GameElement{
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
        super(ElementName.STARTPOINT);
        Gson gson = new Gson();
        StartPoint startPoint = new StartPoint();
        startPoint.isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
