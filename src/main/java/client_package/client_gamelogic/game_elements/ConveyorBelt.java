package client_package.client_gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Color;
import gamelogic.Direction;

import java.io.IOException;

/**
 * @author Mark Ringer
 */
public class ConveyorBelt extends GameElement{

    private Color color;
    int speed;

    /**
     * @param color
     * @param targetDirection
     * @param originDirection1
     * @param originDirection2
     * @throws IOException
     */
    public ConveyorBelt(Color color, Direction targetDirection, Direction originDirection1, Direction originDirection2) throws IOException {
        visualOrder = 1;
        orientations.add(targetDirection);
        orientations.add(originDirection1);
        orientations.add(originDirection2);
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ){
            speed =2;
        }
        else if (color == Color.GREEN) {
            speed =1;
        }
        else throw new IOException("Color not found");
    }

    /**
     * @param color
     * @param targetDirection
     * @param originDirection
     * @throws IOException
     */
    public ConveyorBelt(Color color,Direction targetDirection, Direction originDirection) throws IOException {
        visualOrder = 1;

        orientations.add(targetDirection);
        orientations.add(originDirection);
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }

    /**
     * @param color
     * @param targetDirection
     * @throws IOException
     */
    public ConveyorBelt(Color color,Direction targetDirection) throws IOException {
        visualOrder = 1;

        orientations.add(targetDirection);
        orientations.add(targetDirection.opposite());
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }

    /**
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public ConveyorBelt(JsonObject jsonObject) throws IOException {

        super(ElementName.CONVEYORBELT);
        visualOrder = 1;
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction targetDirection = null;
        if(orientations.size()>0){
            targetDirection = Direction.parseDirection(orientations.get(0).getAsString());

        }
        Direction originDirection1;
        Direction originDirection2;
        speed = jsonObject.get("speed").getAsInt();

        switch (speed){
            case 0 -> color = Color.GREEN;
            case 1 ->
                    color = Color.GREEN;

            case 2 ->
                    color = Color.BLUE;
            default ->
                    throw new IOException("Invalid speed parameter.  speed: "+speed);
        }
        gamelogic.game_elements.ConveyorBelt conveyorBelt;
        switch (orientations.size()){
            case 0 ->{

            }
            case 1 ->{

                this.orientations.add(targetDirection.opposite());
            }
            case 2 ->{
                originDirection1 = Direction.parseDirection(orientations.get(1).getAsString());
                this.orientations.add(targetDirection);
                this.orientations.add(originDirection1);
            }
            case 3 ->{
                originDirection1 = Direction.parseDirection(orientations.get(1).getAsString());
                originDirection2 = Direction.parseDirection(orientations.get(2).getAsString());
                this.orientations.add(targetDirection);
                this.orientations.add(originDirection1);
                this.orientations.add(originDirection2);

            }
            default -> throw new IOException("Illegal Number of arguments. Number of arguments: "+orientations.size());
        }
    }
    public Color getColor() {
        return color;
    }
}
