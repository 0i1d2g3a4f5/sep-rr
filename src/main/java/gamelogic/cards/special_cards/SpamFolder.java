package gamelogic.cards.special_cards;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;

import static gamelogic.cards.CardName.*;

import java.util.Stack;

public class SpamFolder extends SpecialCard {
    public SpamFolder(){
        super(SPAM_FOLDER);
    }
    public void discard() {

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
