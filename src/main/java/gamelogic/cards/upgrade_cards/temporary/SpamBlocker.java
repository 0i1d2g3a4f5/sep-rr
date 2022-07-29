package gamelogic.cards.upgrade_cards.temporary;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

import java.util.ArrayList;
import java.util.Stack;

public class SpamBlocker extends UpgradeCard {
    public SpamBlocker() {
        super(CardName.SPAM_BLOCKER);
    }
    public void discard() {

    }

    /**
     * @author Qinyi
     * Replace each SPAM damage card in your hand with
     * a card from the top of your deck.
     * Immediately discard the SPAM damage cards by placing them in the SPAM damage card draw pile.
     * If you draw new SPAM damage cards from your deck, keep them in your hand for this round.
     */
    @Override
    public void activateCard() {
        ArrayList<Card> handcardPile =player.getHandCards();
        for (Card card:handcardPile) {
            if(card.getCardName()==CardName.SPAM) handcardPile.remove(card);
            player.getHandCards().add(player.DeckPile().pop());
        }
    }
}
