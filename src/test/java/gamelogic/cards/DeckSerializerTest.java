package gamelogic.cards;

import com.google.gson.JsonElement;
import gamelogic.cards.damage_card.Spam;
import gamelogic.cards.damage_card.Virus;
import gamelogic.cards.upgrade_cards.permanent.CorruptionWave;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DeckSerializerTest {
    Stack<Card> deck = new Stack<>();
    @BeforeEach
    void before(){
        deck.add(new Virus());
        deck.add(new Spam());
        deck.add(new CorruptionWave());
    }
    @AfterEach
    void after(){
        deck.clear();
    }

    @Test
    void deserializeCard() throws IOException {
        DeckSerializer serializer = new DeckSerializer();

        JsonElement serializedDeck = serializer.serializeDeck(deck);

        System.out.println(serializedDeck);

        Stack<Card> newdeck = serializer.deserializeDeck(serializedDeck);
        assertEquals(deck,newdeck);

    }

    @Test
    void deserializeDeck() {
    }


    @Test
    void serializeDeck() {
        DeckSerializer serializer = new DeckSerializer();

        System.out.println(serializer.serializeDeck(deck));
    }
}