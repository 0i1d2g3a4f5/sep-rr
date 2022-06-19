package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.game_elements.robot.Robot;
import gamelogic.game_elements.*;

import java.util.ArrayList;
import java.util.Objects;

public class GameField {
    private Position position;

    public boolean isActive() {
        return isActive;
    }

    private boolean isActive=true;


    private Robot robot;

    private ArrayList<GameElement> elements = new ArrayList<>();



     public GameField(Position position) {
         elements.add(new Empty());
         this.position = position;

    }

    public GameField(int y, int x) {
        elements.add(new Empty());
        this.position = new Position(y,x);

    }



    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public ArrayList<GameElement> getElements() {
        return elements;
    }

    public Robot getRobot() {
        return robot;
    }
    public void removeRobot(){
         robot = null;
    }


    public boolean contains(ElementName elementName){
        for (GameElement element:elements) {
            if(element.getType()==elementName) return true;
        }
        return false;
    }

    public boolean checkWall(Direction direction){
        for (GameElement element:elements) {
            if(element.getType()==ElementName.WALL&& element.orientations.contains(direction.toString())) return true;
        }
        return false;
    }
    public boolean contains(Robot robot){
         if(this.robot.equals(robot)) return true;
         return false;
    }

    public boolean addElement(GameElement element) {
        if (elements.size() == 1 && elements.get(0).getType() == ElementName.EMPTY)
            elements.remove(0);
        element.setGameField(this);
        elements.add(element);
        return true;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return isActive == gameField.isActive && position.equals(gameField.position) && Objects.equals(robot, gameField.robot) && elements.equals(gameField.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, isActive, robot, elements);
    }
}
