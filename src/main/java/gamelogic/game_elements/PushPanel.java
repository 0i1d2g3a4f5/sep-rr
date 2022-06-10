package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class PushPanel extends GameElement{
    Direction direction;
    public PushPanel(Direction direction){
        this.direction=direction;
        type =ElementName.PUSHPANEL;
    }

    public static PushPanel fromJson(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction direction = Direction.parseDirection(orientations.get(0).getAsString());
        PushPanel pushPanel = new PushPanel(direction);
        pushPanel.isOnBoard = jsonObject.get("isOnBoard").getAsString();

        return pushPanel;
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
        return jsonObject;
    }
}
