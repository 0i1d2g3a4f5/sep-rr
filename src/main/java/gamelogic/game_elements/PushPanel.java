package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Direction;

import java.io.IOException;

public class PushPanel extends GameElement implements Activatable {
    Direction direction;
    public PushPanel(Direction direction){
        this.direction=direction;
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
        PushPanel pushPanel = new PushPanel(direction);
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
        return jsonObject;
    }

    @Override
    public void activate() {

    }

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameElement o) {
        switch (o.getType()){
            case CHECKPOINT, GEAR,ENERGYSPACE-> {
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