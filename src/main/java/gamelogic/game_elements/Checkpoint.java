package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class Checkpoint extends GameElement{
    public static int numberOfCheckpoints;
    int count;
    public Checkpoint(int count){
        this.count =count;
        numberOfCheckpoints++;
    }

    public static Checkpoint fromJson(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();

        Checkpoint checkpoint = new Checkpoint(jsonObject.get("count").getAsInt());
        checkpoint.isOnBoard = jsonObject.get("isOnBoard").getAsString();



        return checkpoint;
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
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }

}
