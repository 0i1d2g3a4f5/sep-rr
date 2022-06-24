package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Direction;
import gamelogic.game_elements.robot.Robot;
import newmessages.MessageEnergy;

import java.io.IOException;

public class EnergySpace extends GameElement implements Activatable {

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
        Gson gson = new Gson();

        EnergySpace energySpace = new EnergySpace(jsonObject.get("count").getAsInt());
        energySpace.isOnBoard = jsonObject.get("isOnBoard").getAsString();


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

    /**
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

    /**@author Ringer
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT, GEAR-> {
                return 1;
            }
            case CONVEYORBELT, LASER,ROBOT,PUSHPANEL -> {
                return -1;
            }
            case ENERGYSPACE -> {
                return 0;
            }
            default -> {
                try {
                    throw new IOException(o.getType()+" is Not Comparable");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
