package gamelogic.game_elements;

import gamelogic.Color;
import gamelogic.Direction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConveyorBeltTest {

    @Test
    void toJson() throws IOException {
        ArrayList list = new ArrayList();
        list.add("top");
        list.add("right");
        list.add("bottom");
        ConveyorBelt belt = new ConveyorBelt(Color.BLUE, Direction.NORTH,Direction.SOUTH);
        belt.isOnBoard = "1B";
        System.out.println(belt.toJson());
    }
}