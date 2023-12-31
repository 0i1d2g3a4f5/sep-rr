package client_package.client_gamelogic.game_elements;

import com.google.gson.JsonObject;

import java.io.IOException;

public class ElementFactory {

    private GameElement constructElement(ElementName name, JsonObject jsonObject) throws IOException {
        switch (name){
            case ANTENNA -> {
                return new Antenna(jsonObject);
            }
            case LASER -> {
                return new Laser(jsonObject);
            }
            case CHECKPOINT -> {
                return new Checkpoint(jsonObject);

            }
            case CONVEYORBELT -> {
                return new ConveyorBelt(jsonObject);
            }
            case ENERGYSPACE -> {
                return new EnergySpace(jsonObject);
            }
            case GEAR -> {
                return new Gear(jsonObject);
            }
            case PUSHPANEL -> {
                return new PushPanel(jsonObject);
            }
            case PIT -> {
                return new Pit(jsonObject);
            }
            case STARTPOINT -> {
                return new StartPoint(jsonObject);
            }
            case WALL -> {
                return new Wall(jsonObject);
            }
            case RESTARTPOINT -> {
                return new RestartPoint(jsonObject);
            }
            case EMPTY -> {
                return new Empty(jsonObject);
            }
            default -> throw new IOException("Type "+name+" not found");
        }
    }

    /**
     * Constructor
     *
     * @param jsonObject
     * @return
     * @throws IOException
     *
     * @author Mark Ringer
     */
    public GameElement createElement(JsonObject jsonObject) throws IOException {
        ElementName name = ElementName.parseElementName(jsonObject.get("type").getAsString());
        return constructElement(name,jsonObject);
    }
}
