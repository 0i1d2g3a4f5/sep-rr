package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;
import gamelogic.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameElement {

    public List<String> orientations = new ArrayList<String>();

    public ElementName getType(){
        return type;
    }

    ElementName type = ElementName.EMPTY;

    public String isOnBoard = "B1";

    public Position position;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameElement(){


    }





    @Override
    public String toString() {
        return "GameElement{" +
                "position=" + position +
                ", elementName=" + type +
                '}';
    }
    /*

    public static GameElement parseElement(ElementName elementName) throws IOException {
        GameElement element;
        switch (elementName){
            case LASER->{
                element = new Laser();
            }
            case CHECKPOINT -> {
                element = Checkpoint.class;
            }
            case CONVEYORBELT -> {
                element = ConveyorBelt.class;
            }
            case ENERGYSPACE -> {
                element = EnergySpace.class;
            }
            case GEAR -> {
                element = Gear.class;
            }
            case PUSHPANEL -> {
                element = PushPanel.class;
            }
            case PIT -> {
                element = Pit.class;
            }
            case STARTPOINT -> {
                element = StartPoint.class;
            }
            case WALL -> {
                element = Wall.class;
            }
            case ANTENNA -> {
                element = Antenna.class;
            }
            case EMPTY -> {
                element = Empty.class;
            }
            default -> throw new IOException("Class not found");
        }
        return element;
    }
    }

     */

    public GameElement(ElementName elementName ){
        this.type = elementName;

    }

    public static GameElement fromJson(JsonObject jsonObject) throws IOException {
        GameElement gameElement;
        ElementName type = ElementName.parseElementName(jsonObject.get("type").toString());
        switch (type){
            case ANTENNA -> {
                //gameElement = new Antenna()
            }
            case LASER -> {
            }
            case CHECKPOINT -> {

            }
            case CONVEYORBELT -> {
            }
            case ENERGYSPACE -> {
            }
            case GEAR -> {
            }
            case PUSHPANEL -> {
            }
            case PIT -> {
            }
            case STARTPOINT -> {
            }
            case WALL -> {
            }
            case EMPTY -> {
            }
        }
        return new Antenna(Direction.NORTH);
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
        //jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;
    }


}
