package gamelogic.game_elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class EnergySpace extends GameElement{

    private int count;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public EnergySpace(int count){
        this.count = count;
        type =ElementName.ENERGYSPACE;
    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */
    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }
}
