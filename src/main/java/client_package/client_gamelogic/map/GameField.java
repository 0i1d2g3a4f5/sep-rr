package client_package.client_gamelogic.map;



import client_package.Client;
import client_package.client_gamelogic.game_elements.ElementName;
import client_package.client_gamelogic.game_elements.Empty;
import client_package.client_gamelogic.game_elements.GameElement;

import client_package.client_gamelogic.game_elements.robot.Robot;
import gamelogic.Direction;
import gamelogic.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class GameField {
    private Position position;
    private GameBoard board;

    public boolean isActive() {
        return isActive;
    }

    private boolean isActive=true;



    private ArrayList<GameElement> elements = new ArrayList<>();



     public GameField(GameBoard board, Position position) {
         this.board = board;
         elements.add(new Empty());
         this.position = position;

    }

    public GameField(GameBoard board, int y, int x) {
        this.board = board;
        elements.add(new Empty());
        this.position = new Position(y,x);

    }

    private void sortElements(){
        Collections.sort(elements, (o1, o2) -> {
            if(o1.visualOrder >o2.visualOrder)
                return -1;
            else if(o2.visualOrder > o1.visualOrder)
                return 1;
            else return 0;
        });
        Collections.reverse(elements);
    }

    /**
     * @auther Ringer
     * @param direction
     * @return
     */
    public GameField getNeighbor(Direction direction){
         switch (direction){
             case TOP -> {
                 return board.getField(position.getY()-1, position.getX());
             }
             case BOTTOM -> {
                 return board.getField(position.getY()+1, position.getX());
             }
             case RIGHT -> {
                 return board.getField(position.getY(), position.getX()+1);
             }
             case LEFT -> {
                 return board.getField(position.getY(), position.getX()-1);
             }
             default -> {
                 return null;
             }
         }
    }



    public ArrayList<GameElement> getElements() {
        sortElements();
        return elements;
    }

    @Override
    public String toString() {
        return "GameField{" +
                "position=" + position +
                ", elements=" + elements +
                '}';
    }

    /**
     * @author Ringer
     */
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


    /**
     * @author Ringer
     */
    public boolean contains(ElementName elementName){
        for (GameElement element:elements) {
            if(element.getType()==elementName) return true;
        }
        return false;
    }

    /**
     * @author Ringer
     */
    public boolean checkWall(Direction direction){
        for (GameElement element:elements) {
            if(element.getType()==ElementName.WALL&& element.orientations.contains(direction)) return true;
            /*
            if(element.getType()==ElementName.WALL) {
                Client.clientLogger.debug("Laser Hit Wall bot orientation fails");
                return true;
            }

             */
        }
        return false;
    }
    /**
     * @author Ringer
     */

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
        GameElement robotElement = null;
        for (GameElement element:elements) {
            if(element.getType()==elementName) robotElement = element;
        }
        elements.remove(robotElement);
    }

    /**
     * @author Ringer
     */
    public boolean addRobot(Robot robot) {
         if(getRobot()==null){
             elements.add(robot);
             return true;
         } else return false;
    }

    /**
     * @author Ringer
     */
    public void removeEnergyCube() {
        for (GameElement element:elements) {
            if(element.getType()==ElementName.ENERGY_CUBE) {
                elements.remove(element);
                return;
            }
        }

    }
}
