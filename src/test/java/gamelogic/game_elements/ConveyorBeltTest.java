package gamelogic.game_elements;

import gamelogic.Color;
import gamelogic.Direction;
import net.jqwik.api.Example;

import java.io.IOException;
import java.util.ArrayList;

class ConveyorBeltTest {

    @Example
    void toJson() throws IOException {
        ArrayList list = new ArrayList();
        list.add("top");
        list.add("right");
        list.add("bottom");
        ConveyorBelt belt = new ConveyorBelt(Color.BLUE, Direction.TOP,Direction.BOTTOM);
        belt.isOnBoard = "1B";
        System.out.println(belt.toJson());
    }
}