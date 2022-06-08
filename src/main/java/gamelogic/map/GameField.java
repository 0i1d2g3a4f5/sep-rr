package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.robot.Robot;
import gamelogic.game_elements.*;

import java.io.IOException;

public class GameField {
    Position key;

    boolean isActive=true;



    private boolean wallNorth = false;


    private boolean wallSouth = false;



    private boolean wallEast = false;


    private boolean wallWest = false;



    LaserBeam laserBeam;

    Robot robot;
    ElementName elementName = ElementName.NO_ELEMENT;



    Checkpoint checkpoint;
    GameElement element;


     public GameField(Position key) {
         this.key = key;

    }


    public void setCheckpoint(int checkpointNumber) {
        this.checkpoint = new Checkpoint(checkpointNumber);
    }
    public void setLaserBeam(Direction direction, int numberOfBeams) {
        this.laserBeam = new LaserBeam(direction,numberOfBeams);
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

    public boolean isWallNorth() {
        return wallNorth;
    }

    public boolean isWallSouth() {
        return wallSouth;
    }

    public void setWallSouth(boolean wallSouth) {
        this.wallSouth = wallSouth;
    }


    public void setWallNorth(boolean wallNorth) {
        this.wallNorth = wallNorth;
    }
    public boolean isWallWest() {
        return wallWest;
    }

    public void setWallWest(boolean wallWest) {
        this.wallWest = wallWest;
    }

    public boolean isWallEast() {
        return wallEast;
    }

    public void setWallEast(boolean wallEast) {
        this.wallEast = wallEast;
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

         this.elementName=element.getType();

         this.element=element;
         this.element.setPosition(key);

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
