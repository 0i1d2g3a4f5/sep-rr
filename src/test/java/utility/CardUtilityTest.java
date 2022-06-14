package utility;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CardUtilityTest {

    @Property
    void propertySearchCard(@ForAll("deck") ArrayList<Card> deck, @ForAll CardName cardName) throws IOException {
        //System.out.println(utility.CardUtility.searchCard(cardName,deck));
        boolean statementCardsAreEqual = (Card.parseCard(cardName)).equals(utility.CardUtility.searchCard(cardName,deck));
        boolean statementDeckContainsCard = deck.contains(Card.parseCard(cardName));
        boolean statementDeckDoesntContainCard = !deck.contains(Card.parseCard(cardName));
        boolean statementCardIsNull = (utility.CardUtility.searchCard(cardName,deck)==null);

        System.out.println("statementCardsAreEqual: "+statementCardsAreEqual);
        System.out.println("statementDeckContainsCard: "+statementDeckContainsCard);
        System.out.println("statementDeckDoesntContainCard: "+statementDeckDoesntContainCard);
        System.out.println("statementCardIsNull: "+statementCardIsNull);
        System.out.println("parsed: "+Card.parseCard(cardName)+"| searched: "+utility.CardUtility.searchCard(cardName,deck));

        assertTrue((statementCardsAreEqual && statementDeckContainsCard)||(statementDeckDoesntContainCard&&statementCardIsNull));

    }




    @Provide
    Arbitrary<ArrayList<Card>> deck() throws IOException {
        Arbitrary<ArrayList<Card>> deckReturn = Arbitraries.just(createCards());
        return deckReturn;

    }
    ArrayList<Card> createCards() throws IOException {

        CardName[] cardNames = CardName.values();
        Arbitrary<Integer> number = Arbitraries.integers().between(0,100);
        int listSize = number.sample();

        ArrayList<Card> deck = new ArrayList<>();

        int deckSize = number.sample();
        for(int i = 0;i<deckSize;i++) {
            int position=number.sample()% cardNames.length;
            //System.out.println("CardName "+cardNames[position]);
            deck.add(Card.parseCard(cardNames[position]));
        }
        return deck;

    }
    @Example
    void testDeckTest() throws IOException {
        //System.out.println(deck());
    }
}