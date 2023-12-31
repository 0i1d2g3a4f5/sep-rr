package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.game_elements.robot.Robot;
import gamelogic.game_elements.*;
import server_package.Server;

import java.util.ArrayList;
import java.util.Objects;

public class GameField {
    private Position position;
    private GameBoard board;
    protected String isOnBoard;

    public String getIsOnBoard() {
        return isOnBoard;
    }

    public void setIsOnBoard(String isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    public boolean isActive() {
        return isActive;
    }

    private boolean isActive=true;



    private ArrayList<GameElement> elements = new ArrayList<>();





    public GameField(GameBoard board, int y, int x) {
        this.board = board;
        elements.add(new Empty());
        this.position = new Position(y,x);

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
        return elements;
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


    /**checks if element with this name is contained in elements
     * @author Ringer
     */
    public boolean contains(ElementName elementName){
        for (GameElement element:elements) {
            if(element.getType()==elementName) return true;
        }
        return false;
    }

    /**
     * checks, if gamefield contains a wall with this direction
     *
     * @author Ringer
     */
    public boolean checkWall(Direction direction){
        for (GameElement element:elements) {
            if(element.getType()==ElementName.WALL&& element.orientations.contains(direction)){

                return true;
            }
        }
        return false;
    }
    /**adds element to elements
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

    /**
     * removes All elements with this Name
     * @author Mark Ringer
     * @param elementName
     */
    public void removeAll(ElementName elementName){
        for (int i = 0; i < elements.size() ; i++) {
            GameElement element = elements.get(i);
            if(element.getType()==elementName) elements.remove(element);
        }
    }
    @Override
    public String toString(){
        String result = "";
        ArrayList<GameElement> elements = getElements();
        result+="Position: "+position;
        result+="Elements: ";

        for (int i = 0; i < elements.size(); i++) {
            if(i != 0){
                result+="|";
            }else
                result+="[";
            if(elements.get(i).getType()==null)
                Server.serverLogger.error("ElementType at Field "+position+ " is null");
            else
                result+=elements.get(i).getType().toString();
            if(i== elements.size()-1)
                result+="]";

        }
        return result;

    }

    /**
     * adds robot to elements
     *
     * @param robot
     * @return
     * @author Mark Ringer
     */
    public boolean addRobot(Robot robot) {
         if(getRobot()==null){
             elements.add(robot);
             return true;
         } else return false;
    }

    public void removeElement(GameElement element){
        elements.remove(element);

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
