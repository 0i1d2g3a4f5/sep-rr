package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.game_elements.robot.Robot;
import gamelogic.game_elements.*;

import java.util.ArrayList;
import java.util.Objects;

public class GameField {
    private Position position;
    private GameBoard board;

    public boolean isActive() {
        return isActive;
    }

    private boolean isActive=true;



    private ArrayList<GameElement> elements = new ArrayList<>();



     public GameField(GameBoard board,Position position) {
         this.board = board;
         elements.add(new Empty());
         this.position = position;

    }

    public GameField(GameBoard board, int x, int y) {
        this.board = board;
        elements.add(new Empty());
        this.position = new Position(y,x);

    }

    public GameField getNeighbor(Direction direction){
         switch (direction){
             case NORTH -> {
                 return board.getField(position.getY()-1, position.getX());
             }
             case SOUTH -> {
                 return board.getField(position.getY()+1, position.getX());
             }
             case EAST -> {
                 return board.getField(position.getY(), position.getX()+1);
             }
             case WEST -> {
                 return board.getField(position.getY(), position.getX()-1);
             }
             default -> {
                 return null;
             }
         }
    }



    public ArrayList<GameElement> getElements() {
        return elements;
    }


    public GameElement getElement(ElementName elementName){
         if(contains(elementName)){
             for (GameElement gameElement:elements) {
                 if(gameElement.getType()==elementName) return gameElement;
             }
         }
         return null;
    }
    public Robot getRobot() {
        return (Robot) getElement(ElementName.ROBOT);
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

    public boolean addElement(GameElement element) {
        if(element.getType()==ElementName.ROBOT) return false;
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
        return isActive == gameField.isActive && position.equals(gameField.position) && elements.equals(gameField.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, isActive, elements);
    }

    public void removeRobot() {
         removeAll(ElementName.ROBOT);
    }
    private void removeAll(ElementName elementName){
        for (GameElement element:elements) {
            if(element.getType()==elementName) elements.remove(element);
        }
    }

    public boolean addRobot(Robot robot) {
         if(getRobot()==null){
             elements.add(robot);
             return true;
         } else return false;
    }
}
