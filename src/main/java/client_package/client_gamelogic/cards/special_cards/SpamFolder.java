package client_package.client_gamelogic.cards.special_cards;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;

import java.util.Stack;

import static gamelogic.cards.CardName.SPAM_FOLDER;

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
    public void activateCard(){
        Stack<Card> discardPile =player.getDiscardPile();
        for (Card card:discardPile) {
            if(card.getCardName()== CardName.SPAM) discardPile.remove(card);
        }
    }
}
