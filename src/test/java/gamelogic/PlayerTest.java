package gamelogic;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.programming_cards.Again;
import gamelogic.cards.programming_cards.BackUp;
import gamelogic.cards.programming_cards.LeftTurn;
import net.jqwik.api.*;
import utility.SearchMethods;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Example
    void playCard() {
        Player player = new Player();

    }



    @Example
    void searchCard() {
        Player player = new Player();
        ArrayList<Card> list = new ArrayList<Card>(Arrays.asList(new Card[]{new Again(),new BackUp(),new LeftTurn()}));
        assertEquals(new Again(), SearchMethods.searchCard(CardName.AGAIN,list));

    }
}