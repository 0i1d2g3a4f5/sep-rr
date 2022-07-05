package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Activatable;
import gamelogic.Color;
import gamelogic.Direction;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameField;


import java.io.IOException;


public class ConveyorBelt extends GameElement implements Activatable {

    int activationOrder = 1;
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
            activationOrder = 1;
        }
        else if (color == Color.GREEN) {
            speed =1;
            activationOrder = 2;
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
        super(ElementName.CONVEYORBELT);
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
        ConveyorBelt conveyorBelt;
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


        isOnBoard = jsonObject.get("isOnBoard").getAsString();


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
        jsonObject.add("orientations",gson.toJsonTree(getOrientationsAsStrings()));
        return jsonObject;

    }
    /**
     * @author Ringer
     */
    @Override
    public void activate() {
        if(gameField.contains(ElementName.ROBOT)){
            Robot robot = gameField.getRobot();

            if(color ==Color.BLUE){
                robot.displace(orientations.get(0));
            }
            robot.displace(orientations.get(0));

        }
    }

    public Color getColor() {
        return color;
    }

    public int getActivationOrder(){
        return activationOrder;
    }

    /**@author Ringer
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Activatable o) {
        if(getActivationOrder()>o.getActivationOrder()) return -1;
        else if (getActivationOrder()<o.getActivationOrder()) return 1;
        return 0;
    }
}
