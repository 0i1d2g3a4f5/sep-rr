package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.Robot.Robot;
import gamelogic.game_elements.*;
import javafx.util.Pair;

import java.io.IOException;

public class GameField {
    Position key;

    boolean isActive=true;
    boolean wallNorth = false;
    boolean wallSouth = false;
    boolean wallEast = false;
    boolean wallWest = false;

    LaserBeam laserBeam;

    Robot robot;
    ElementName elementName = ElementName.NO_ELEMENT;

    Checkpoint checkpoint;
    GameElement element;


     public GameField(Position key) {
         this.key = key;

    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public GameElement getElement() {
        return element;
    }

    public Robot getRobot() {
        return robot;
    }
    public void removeRobot(){
         robot = null;
    }

    public void editWall(Direction direction, boolean active){
         switch (direction){
             case NORTH -> {
                 wallNorth = active;
             }
             case SOUTH -> {
                 wallSouth = active;
             }
             case WEST -> {
                 wallWest = active;
             }
             case EAST -> {
                 wallEast = active;
             }
             default -> {
                 try {
                     throw new IOException();
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
         }
    }


    public void addElement(GameElement element){

         this.elementName=element.getElementName();
         this.element=element;

    }
    public void removeElement(){
         this.elementName=ElementName.NO_ELEMENT;
         this.element=null;
    }

    @Override
    public String toString() {
        return "GameField{" +
                "key=" + key +
                ", wallTop=" + wallNorth +
                ", wallBottom=" + wallSouth +
                ", wallRight=" + wallEast +
                ", wallLeft=" + wallWest +
                ", elementName=" + elementName +
                '}';
    }



    public Position getKey() {
        return key;
    }
}
