package gamelogic.cards;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CardNameTest {

    @Test
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



    @Test
    void testToString() {
    }
}