package gamelogic.map;

import gamelogic.Direction;
import gamelogic.Position;
import gamelogic.robot.Robot;
import gamelogic.game_elements.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameField {
    Position key;

    boolean isActive=true;


    Robot robot;

    ArrayList<GameElement> elements = new ArrayList<>();



     public GameField(Position key) {
         elements.add(new Empty());
         this.key = key;

    }

    public GameField(int y, int x) {
        elements.add(new Empty());
        this.key = new Position(y,x);

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




    public boolean addElement(GameElement element) {
        if (elements.size() == 1 && elements.get(0).getType() == ElementName.EMPTY)
            elements.remove(0);
        elements.add(element);
        return true;
    }

    public Position getKey() {
        return key;
    }
}
