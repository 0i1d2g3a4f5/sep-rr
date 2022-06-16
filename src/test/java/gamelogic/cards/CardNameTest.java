package gamelogic.cards;


import gamelogic.map.GameField;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Test;
import server.Application;
import server.Client;
import server.Server;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CardNameTest {

    @Example
    void parseCardName() {
        CardName name1 = CardName.VIRUS;
        CardName name2 = null;
        try {
            name2 = CardName.parseCardName("Virus");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(name1,name2);
    }




    @Example
    void testToString() {
    }
}