package client_package.client_gamelogic.game_elements;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


import java.io.IOException;

public class EnergySpace extends GameElement {

    private int count;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public EnergySpace(int count){
        visualOrder = 1;
        this.count = count;
        type = ElementName.ENERGYSPACE;
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public EnergySpace (JsonObject jsonObject) throws IOException {
        super(ElementName.ENERGYSPACE);
        visualOrder = 1;
        this.count = jsonObject.get("count").getAsInt();
    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */
    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("count",new JsonPrimitive(count));
        return jsonObject;
    }


    /**@author Ringer
     * @param o the object to be compared.
     * @return
     */

}
