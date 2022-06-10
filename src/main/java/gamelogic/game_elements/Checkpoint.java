package gamelogic.game_elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Checkpoint extends GameElement{
    public static int numberOfCheckpoints;
    int count;
    public Checkpoint(int count){
        this.count =count;
        numberOfCheckpoints++;
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
