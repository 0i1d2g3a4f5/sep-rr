package client_package.client_gamelogic.game_elements.game_elements;

import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;

import java.io.IOException;


public class ConveyorBelt extends GameElement{

    private Color color;
    int speed;

    /**
     * @author Ringer
     */
    public ConveyorBelt(Color color, Direction targetDirection, Direction originDirection1, Direction originDirection2) throws IOException {
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
     * @author Ringer
     */
    public ConveyorBelt(Color color,Direction targetDirection, Direction originDirection) throws IOException {

        orientations.add(targetDirection);
        orientations.add(originDirection);
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }

    /**
     * @author Ringer
     */
    public ConveyorBelt(Color color,Direction targetDirection) throws IOException {

        orientations.add(targetDirection);
        orientations.add(targetDirection.opposite());
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */
    public ConveyorBelt(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        JsonArray orientations = gson.fromJson(jsonObject.get("orientations"), JsonArray.class);
        Direction targetDirection = Direction.parseDirection(orientations.get(0).getAsString());
        Direction originDirection1;
        Direction originDirection2;
        int speed = jsonObject.get("speed").getAsInt();
        Color color;
        switch (speed){
            case 1 ->
                color = Color.GREEN;

            case 2 ->
                color = Color.BLUE;
            default ->
                throw new IOException("Invalid speed parameter");
        }
        ConveyorBelt conveyorBelt;
        switch (orientations.size()){
            case 1 ->{
                conveyorBelt = new ConveyorBelt(color, targetDirection);
            }
            case 2 ->{
                originDirection1 = Direction.parseDirection(orientations.get(1).getAsString());
                conveyorBelt = new ConveyorBelt(color,targetDirection, originDirection1);
            }
            case 3 ->{
                originDirection1 = Direction.parseDirection(orientations.get(1).getAsString());
                originDirection2 = Direction.parseDirection(orientations.get(2).getAsString());
                conveyorBelt = new ConveyorBelt(color,targetDirection, originDirection1,originDirection2);

            }
            default -> throw new IOException("Illegal Number of arguments. Number of arguments: "+orientations.size());
        }
    }
    public Color getColor() {
        return color;
    }
}