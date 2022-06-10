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


    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public GameElement(){


    }


    @Override
    public String toString() {
        return "GameElement{" +
                "orientations=" + orientations +
                ", type=" + type +
                ", isOnBoard='" + isOnBoard + '\'' +
                ", gson=" + gson +
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
        GameElement element;

        switch (ElementName.parseElementName(jsonObject.get("type").getAsString())){
            case ANTENNA -> {
                element = Antenna.fromJson(jsonObject);
            }
            case LASER -> {
                element = Laser.fromJson(jsonObject);
            }
            case CHECKPOINT -> {
                element = Checkpoint.fromJson(jsonObject);

            }
            case CONVEYORBELT -> {
                element = ConveyorBelt.fromJson(jsonObject);
            }
            case ENERGYSPACE -> {
                element = EnergySpace.fromJson(jsonObject);
            }
            case GEAR -> {
                element = Gear.fromJson(jsonObject);
            }
            case PUSHPANEL -> {
                element = PushPanel.fromJson(jsonObject);
            }
            case PIT -> {
                element = Pit.fromJson(jsonObject);
            }
            case STARTPOINT -> {
                element = StartPoint.fromJson(jsonObject);
            }
            case WALL -> {
                element = Wall.fromJson(jsonObject);
            }
            case EMPTY -> {
                element = Empty.fromJson(jsonObject);
            }
            default -> throw new IOException("Type not found");
        }
        return element;
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
