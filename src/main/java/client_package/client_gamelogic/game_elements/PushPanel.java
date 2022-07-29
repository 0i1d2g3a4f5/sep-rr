package client_package.client_gamelogic.game_elements;

import client_package.Client;
import client_package.client_gamelogic.game_elements.robot.Robot;
import com.google.gson.*;
import gamelogic.Activatable;
import gamelogic.Direction;
import gamelogic.Game;

import java.io.IOException;
import java.util.ArrayList;
public class PushPanel extends GameElement {
    int activationOrder = 3;
    Direction direction;
    ArrayList<Integer> activateRegisters;

    public PushPanel(Direction direction, ArrayList<Integer> activationRegisters) {
        visualOrder = 2;
        this.direction = direction;
        orientations.add(direction);
        this.activateRegisters = activationRegisters;
        type = ElementName.PUSHPANEL;
    }

    /**builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     * @author Ringer
     *
     */
    public PushPanel(JsonObject jsonObject) throws IOException {
        super(ElementName.PUSHPANEL);
        visualOrder = 2;
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        if(orientations.size()>0){

            direction = Direction.parseDirection(orientations.get(0).getAsString());
            this.orientations.add(direction);
            Client.clientLogger.debug("PushPanel orientations as Json: "+orientations+ "PushPanel orientations after "+this.orientations);
        }

        JsonArray jsonArrayRegisters = jsonObject.get("registers").getAsJsonArray();
        ArrayList<Integer> activationRegisters = new ArrayList<>();
        for (JsonElement activeRegister : jsonArrayRegisters) {
            activationRegisters.add(activeRegister.getAsInt());
        }

        this.activateRegisters = activationRegisters;

    }
    public ArrayList<Integer> getActivateRegisters(){
        return activateRegisters;
    }
}

