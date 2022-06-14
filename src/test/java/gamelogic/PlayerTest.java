package gamelogic;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.programming_cards.Again;
import gamelogic.cards.programming_cards.BackUp;
import gamelogic.cards.programming_cards.LeftTurn;
import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Example
    void playCard() {
        Player player = new Player();

    }
/*
    @Property
    void propertySearchCard(@ForAll("deck") ArrayList<Card> deck, @ForAll CardName cardName){
        System.out.println(utility.CardUtility.searchCard(cardName,deck));

    }

 */

    @Provide
    ArrayList<Card> deck() throws IOException {
        CardName[] cardNames = CardName.values();
        ArrayList<Card> deck = new ArrayList<>();
        Arbitrary<Integer> number = Arbitraries.integers();
        for(int i = 0;i<number.sample();i++) {
            deck.add(Card.parseCard(cardNames[number.sample()]));
        }
        return deck;
    }

    @Example
    void searchCard() {
        Player player = new Player();
        ArrayList<Card> list = new ArrayList<Card>(Arrays.asList(new Card[]{new Again(),new BackUp(),new LeftTurn()}));
        assertEquals(new Again(),utility.CardUtility.searchCard(CardName.AGAIN,list));

    }
}