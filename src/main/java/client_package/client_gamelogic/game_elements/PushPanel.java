package client_package.client_gamelogic.game_elements;

import client_package.client_gamelogic.game_elements.robot.Robot;
import com.google.gson.*;
import gamelogic.Direction;
import gamelogic.Game;

import java.io.IOException;
import java.util.ArrayList;
public class PushPanel extends GameElement{
    int activationOrder = 3;
    Direction direction;
    ArrayList<Integer> activateRegisters;
    public PushPanel(Direction direction, ArrayList<Integer> activationRegisters){
        this.direction=direction;
        this.activateRegisters=activationRegisters;
        type = ElementName.PUSHPANEL;
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
    }

}
