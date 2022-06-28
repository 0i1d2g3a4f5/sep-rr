package gamelogic.game_elements;

import com.google.gson.*;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.Game;
import gamelogic.game_elements.robot.Robot;
import java.io.IOException;
import java.util.ArrayList;
public class PushPanel extends GameElement implements Activatable {
    Direction direction;
    ArrayList<Integer> activateRegisters;
    public PushPanel(Direction direction, ArrayList<Integer> activationRegisters){
        this.direction=direction;
        this.activateRegisters=activationRegisters;
        type =ElementName.PUSHPANEL;
    }
    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public PushPanel(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        JsonArray jsonArrayRegisters = jsonObject.get("registers").getAsJsonArray();
        ArrayList<Integer> activationRegisters = new ArrayList<>();
        for (JsonElement activeRegister:jsonArrayRegisters) {
            activationRegisters.add(activeRegister.getAsInt());
        }
        PushPanel pushPanel = new PushPanel(direction,activationRegisters);
        pushPanel.isOnBoard = jsonObject.get("isOnBoard").getAsString();
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
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        JsonArray jsonArrayRegisters = new JsonArray();
        for (int register:activateRegisters) {
            jsonArrayRegisters.add(register);
        }
        jsonObject.add("registers",jsonArrayRegisters);
        return jsonObject;
    }
    /**
     * @author Ringer
     */
    @Override
    public void activate() {
        if(gameField.contains(ElementName.ROBOT)&& (activateRegisters.contains(Game.getInstance().getActiveRegister()+1))){
            Robot robot = gameField.getRobot();
            robot.displace(orientations.get(0));
        }
    }
    /**
     * @param o the object to be compared.
     * @return
     */

    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT, GEAR,ENERGYSPACE,EMPTY-> {
                return 1;
            }
            case CONVEYORBELT, LASER,ROBOT -> {
                return -1;
            }
            case PUSHPANEL -> {
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
