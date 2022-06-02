package gamelogic.map;

import gamelogic.Game;
import gamelogic.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void testToString() {
    }

    @Test
    void addField() {
        GameBoard board = new GameBoard();
        GameField field = new GameField(new Position(1,2));
        board.addField(field);
        assertEquals(board.getField(1,2),field);
    }

    @Test
    void getField() {
    }

    @Test
    void getRegistry() {
        ArrayList list = new ArrayList();
        Game game = Game.getInstance();
        game.setup();
        game.board.boardMap.forEach((key,value)->{
            if(value.element!=null){
                list.add(value.element);
            }
        });
        assertEquals(list,game.board.getRegistry());
    }

    @Test
    void changeActiveStatus() {
    }
}