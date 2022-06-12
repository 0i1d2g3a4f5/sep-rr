package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Color;
import gamelogic.Direction;


import java.io.IOException;
import java.util.List;


public class ConveyorBelt extends GameElement {
    Color color;
    int speed;

    public ConveyorBelt(Color color, Direction targetDirection, Direction originDirection1, Direction originDirection2) throws IOException {
        orientations.add(targetDirection.toString());
        orientations.add(originDirection1.toString());
        orientations.add(originDirection2.toString());
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }
    public ConveyorBelt(Color color,Direction targetDirection, Direction originDirection) throws IOException {

        orientations.add(targetDirection.toString());
        orientations.add(originDirection.toString());
        type = ElementName.CONVEYORBELT;
        if(color ==Color.BLUE ) speed =2;
        else if (color == Color.GREEN) speed =1;
        else throw new IOException("Color not found");
    }

    public ConveyorBelt(Color color,Direction targetDirection) throws IOException {

        orientations.add(targetDirection.toString());
        orientations.add(targetDirection.opposite().toString());
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
    public static ConveyorBelt fromJson(JsonObject jsonObject) throws IOException {
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


        conveyorBelt.isOnBoard = jsonObject.get("isOnBoard").getAsString();



        return conveyorBelt;
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
        jsonObject.add("speed",new JsonPrimitive(speed) );
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;

    }

}
