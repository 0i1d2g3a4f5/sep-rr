package gamelogic.cards;

import com.google.gson.JsonElement;

import net.jqwik.api.Example;
import net.jqwik.api.lifecycle.AfterTry;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeckSerializerTest {
    Stack<Card> deck = new Stack<>();
/*
    @Provide
    List<CardName> cardNames(){
        List<CardName> list = Arrays.stream(CardName.values()).toList();
        return list;
    }

/*
    @Property

    void property(@ForAll("cardNames") CardName cardName ) throws IOException {
        Card card1= Card.parseCard(cardName);
        deck.add(card1);


        assertTrue(deck.contains(card1));

    }

 */
    @AfterTry
    void after(){
        deck.clear();
    }

    @BeforeTry
    void before() throws IOException {
        CardName[] cards = CardName.values();
        Random random = new Random();


        int deckSize = 50;
        for(int i = 0; i<deckSize;i++){
            deck.add(new CardFactory().createCard(cards[random.nextInt(cards.length)]));
        }

    }


    @Example
    void deserializeCard() throws IOException {
        /*
        DeckSerializer serializer = new DeckSerializer();

        JsonElement serializedDeck = serializer.serializeDeck(deck);

        System.out.println(serializedDeck);

        Stack<Card> newdeck = serializer.deserializeDeck(serializedDeck);
        assertEquals(deck,newdeck);

         */

    }

    @Example
    void deserializeDeck() {
    }


    @Example
    void serializeDeck() throws IOException {
        /*
        DeckSerializer serializer = new DeckSerializer();

        JsonElement jsonElement = serializer.serializeDeck(deck);

        Stack<Card> deckAfter = serializer.deserializeDeck(jsonElement);

        System.out.println("DeckBefore: "+deck);
        System.out.println("DeckAfter: "+deckAfter);

        assertEquals(deck,deckAfter);

         */
    }
}