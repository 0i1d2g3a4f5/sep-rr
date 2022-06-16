package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    /**
     * @author Ringer
     * decides wich object is built from the jsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    //Factory



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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameElement)) return false;
        GameElement element = (GameElement) o;
        return orientations.equals(element.orientations) && getType() == element.getType() && isOnBoard.equals(element.isOnBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientations, getType(), isOnBoard);
    }
}
