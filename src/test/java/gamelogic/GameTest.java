package gamelogic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getInstance(){
        Game game = Game.getInstance();

        assertEquals(true,game.getContinueGame());
    }

    @Test
    void deleteInstance() {
        Game game = Game.getInstance();
        Game.deleteInstance();
        assertFalse(Game.instanceExists());
    }

    @Test
    void testGetInstance() {
        assertFalse(Game.instanceExists());
        Game game = Game.getInstance();
        assertTrue(Game.instanceExists());
        assertNotNull(game);
    }



    @Test
    void startGame() {
        /*
        Game game = Game.getInstance();
        assertNotEquals(true,game.getContinueGame());
        game.startGame();
        assertNotEquals(true,game.getContinueGame());

         */
    }


    @Test
    void winningGame() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Game.deleteInstance();
    }

    @Test
    void join() {
    }
}