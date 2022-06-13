package gamelogic.cards;

import com.google.gson.JsonElement;
import gamelogic.cards.damage_card.Spam;
import gamelogic.cards.damage_card.Virus;
import gamelogic.cards.upgrade_cards.permanent.CorruptionWave;

import net.jqwik.api.Example;
import net.jqwik.api.lifecycle.AfterTry;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DeckSerializerTest {
    Stack<Card> deck = new Stack<>();
    @BeforeTry
    void before(){
        deck.add(new Virus());
        deck.add(new Spam());
        deck.add(new CorruptionWave());
    }
    @AfterTry
    void after(){
        deck.clear();
    }

    @Example
    void deserializeCard() throws IOException {
        DeckSerializer serializer = new DeckSerializer();

        JsonElement serializedDeck = serializer.serializeDeck(deck);

        System.out.println(serializedDeck);

        Stack<Card> newdeck = serializer.deserializeDeck(serializedDeck);
        assertEquals(deck,newdeck);

    }

    @Example
    void deserializeDeck() {
    }


    void propertyTest(){

    }

    @Example
    void serializeDeck() throws IOException {
        DeckSerializer serializer = new DeckSerializer();

        JsonElement jsonElement = serializer.serializeDeck(deck);

        Stack<Card> deckAfter = serializer.deserializeDeck(jsonElement);

        System.out.println("DeckBefore: "+deck);
        System.out.println("DeckAfter: "+deckAfter);

        assertEquals(deck,deckAfter);
    }
}