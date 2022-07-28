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
import server_package.Server;


import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.concurrent.TimeUnit;


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

        switch (orientations.size()){
            case 0 ->{
                throw new MalformedParametersException("ConveyorBelt has 0 orientations");
            }
            case 1 ->{

                this.orientations.add(targetDirection);
                originDirection1 = targetDirection.opposite();
                this.orientations.add(originDirection1);
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
    public void activate() throws InterruptedException {
        Server.serverLogger.info("checking "+ type +" "+color+ " at "+ gameField.getPosition());
        Server.serverLogger.debug("Game-field of this ConveyorBelt: "+gameField.toString());

        if(gameField.contains(ElementName.CHECKPOINT)){
            Server.serverLogger.info("activate "+ type +" "+color+ " at "+ gameField.getPosition());
            Server.serverLogger.debug("ConveyorBelt orientations: "+ orientations);
            Checkpoint checkPoint = (Checkpoint) gameField.getElement(ElementName.CHECKPOINT);

            if(checkPoint.movedByCBelt){
                Server.serverLogger.debug("Checkpoint already moved by conveyor Belt");
                return;
            }


            checkPoint.movedByCBelt=true;

            GameField nextField = gameField.getNeighbor(orientations.get(0));




            //checkPoint.setEnteredConveyorBelt(orientations.get(0).opposite());
            if(color ==Color.BLUE){

                checkPoint.displace(orientations.get(0));
                GameElement element;
                if((element = checkPoint.getGameField().getElement(ElementName.CONVEYORBELT)) !=null){

                    ConveyorBelt nextBelt = (ConveyorBelt) element;
                    nextField = nextField.getNeighbor(nextBelt.orientations.get(0));

                    TimeUnit.MILLISECONDS.sleep(10);



                    //checkPoint.setEnteredConveyorBelt(nextBelt.orientations.get(0).opposite());
                    checkPoint.displace(nextBelt.orientations.get(0));


                } else {

                    // checkPoint.displace(orientations.get(0));
                }
            } else{
                checkPoint.displace(orientations.get(0));

            }

        }

        if(gameField.contains(ElementName.ROBOT)){
            Server.serverLogger.info("activate "+ type +" "+color+ " at "+ gameField.getPosition());
            Server.serverLogger.debug("ConveyorBelt orientations: "+ orientations);
            Robot robot = gameField.getRobot();
            if(robot.movedByCBelt){
                Server.serverLogger.debug("Robot already moved by conveyor Belt");
                return;
            }
            robot.movedByCBelt=true;

            GameField nextField = gameField.getNeighbor(orientations.get(0));

            handleConveyorTurnsRobot(robot, nextField);


            //robot.setEnteredConveyorBelt(orientations.get(0).opposite());
            if(color ==Color.BLUE){

                robot.displace(orientations.get(0));
                GameElement element;
                if((element = robot.getGameField().getElement(ElementName.CONVEYORBELT)) !=null){

                    ConveyorBelt nextBelt = (ConveyorBelt) element;
                    nextField = nextField.getNeighbor(nextBelt.orientations.get(0));

                    handleConveyorTurnsRobot(robot, nextField);

                    //robot.setEnteredConveyorBelt(nextBelt.orientations.get(0).opposite());
                    TimeUnit.MILLISECONDS.sleep(10);
                    robot.displace(nextBelt.orientations.get(0));
                    if((robot.getGameField().getElement(ElementName.CONVEYORBELT)) ==null){
                        robot.setEnteredConveyorBelt(null);
                    }

                } else {
                    robot.setEnteredConveyorBelt(null);
                   // robot.displace(orientations.get(0));
                }
            } else{
                robot.displace(orientations.get(0));

            }

        }
        try {
            TimeUnit.MILLISECONDS.sleep(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleConveyorTurnsRobot(Robot robot, GameField nextField) {
        Server.serverLogger.debug("handleConveyorTurn: "+nextField);
        if(nextField.contains(ElementName.CONVEYORBELT)){
            ConveyorBelt nextBelt = (ConveyorBelt) nextField.getElement(ElementName.CONVEYORBELT);
            if(orientations.get(0).left() == nextBelt.orientations.get(0)){
                robot.left();
            }else if(orientations.get(0).right() == nextBelt.orientations.get(0)){
                robot.right();
            }
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
