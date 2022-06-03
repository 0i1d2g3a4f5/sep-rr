package gamelogic.Card.special_cards;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;

import static gamelogic.Card.CardName.*;

import java.util.Stack;

public class SpamFolder extends Card {
    public SpamFolder(){
        super(SPAM_FOLDER);
    }

    /**
     * @author Qinyi
     * Permanently discard an SPAM damage card from your discard pile to the SPAM damage card draw pile.
     */
    @Override
    public void PlayCard(){
        Stack<Card> discardPile =player.getDiscardPile();
        for (Card card:discardPile) {
            if(card.getCardName()==CardName.SPAM) discardPile.remove(card);
        }
    }
}
