package gamelogic;

import gamelogic.map.MapName;
import net.jqwik.api.Example;
import net.jqwik.api.lifecycle.AfterTry;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Example
    void getInstance(){
        Game game = Game.getInstance();

        assertEquals(true,game.getContinueGame());
    }

    @Example
    void deleteInstance() {
        Game game = Game.getInstance();
        Game.deleteInstance();
        assertFalse(Game.instanceExists());
    }

    @Example
    void testGetInstance() {
        assertFalse(Game.instanceExists());
        Game game = Game.getInstance();
        assertTrue(Game.instanceExists());
        assertNotNull(game);
    }



    @Example
    void startGame() throws IOException, InterruptedException {

        Game game = Game.getInstance();

        /* game.startGame(MapName.DIZZY_HIGHWAY);
        assertNotNull(game.board); */


    }


    @Example
    void winningGame() {
    }

    @BeforeTry
    void setUp() {
    }

    @AfterTry
    void tearDown() {
        Game.deleteInstance();
    }

    @Example
    void join() {
    }
}