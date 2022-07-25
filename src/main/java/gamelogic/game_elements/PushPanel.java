package gamelogic.game_elements;

import com.google.gson.*;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.Game;
import gamelogic.game_elements.robot.Robot;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;
public class PushPanel extends GameElement implements Activatable {
    int activationOrder = 3;
    Direction direction;
    ArrayList<Integer> activateRegisters;
    public PushPanel(Direction direction, ArrayList<Integer> activationRegisters){
        super(ElementName.PUSHPANEL);
        this.direction=direction;
        this.orientations.add(direction);
        this.activateRegisters=activationRegisters;

    }
    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public PushPanel(JsonObject jsonObject) throws IOException {
        super(ElementName.PUSHPANEL);
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        JsonArray jsonArrayRegisters = jsonObject.get("registers").getAsJsonArray();
        ArrayList<Integer> activationRegisters = new ArrayList<>();
        for (JsonElement activeRegister:jsonArrayRegisters) {
            activationRegisters.add(activeRegister.getAsInt());
        }

        this.activateRegisters = activationRegisters;
        this.direction = direction;
        this.orientations.add(direction);
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
        jsonObject.add("orientations",gson.toJsonTree(getOrientationsAsStrings()));
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
        Server.serverLogger.info("Activate " + type);
        if(gameField.contains(ElementName.ROBOT)&& (activateRegisters.contains(Game.getInstance().getActiveRegister()+1))){
            Robot robot = gameField.getRobot();
            robot.displace(orientations.get(0));
        }
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }
    /**
     * @param o the object to be compared.
     * @return
     */

    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }
}
