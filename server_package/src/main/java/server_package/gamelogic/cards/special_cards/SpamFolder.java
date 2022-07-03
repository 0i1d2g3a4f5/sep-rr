package server_package.gamelogic.cards.special_cards;

import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardName;

import java.util.Stack;

public class SpamFolder extends SpecialCard {
    public SpamFolder(){
        super(CardName.SPAM_FOLDER);
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
            if(card.getCardName()==CardName.SPAM) discardPile.remove(card);
        }
    }
}
