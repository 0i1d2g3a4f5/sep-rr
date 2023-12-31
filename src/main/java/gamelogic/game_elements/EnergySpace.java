package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.game_elements.robot.Robot;
import messages.MessageEnergy;

import java.io.IOException;

public class EnergySpace extends GameElement implements Activatable {

    int activationOrder = 7;
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
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public EnergySpace (JsonObject jsonObject) throws IOException {
        super(ElementName.ENERGYSPACE);
        Gson gson = new Gson();

        count = jsonObject.get("count").getAsInt();
        isOnBoard = jsonObject.get("isOnBoard").getAsString();


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

    /**activates element
     * @author Ringer
     */
    @Override
    public void activate() {
        if(gameField.contains(ElementName.ROBOT)){
            Robot robot =(Robot) gameField.getElement(ElementName.ROBOT);
            robot.getPlayer().addEnergyCube();
            robot.getPlayer().getClient().sendAll(new MessageEnergy(robot.getPlayer().getClient().getId(),1,"EnergyCube"));
        }
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    /**
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }

    /**@author Ringer
     * @param o the object to be compared.
     * @return
     */

}
