package client_package.client_gamelogic.cards.upgrade_cards.temporary;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

import java.util.ArrayList;

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
        ArrayList<Card> handcardPile =player.getHandCards();//don't know whether it is handcardpile or discardpile
        for (Card card:handcardPile) {
            if(card.getCardName()== CardName.SPAM) handcardPile.remove(card);
            //replace the spam cards with the new drew cards
            player.getHandCards().add(player.DeckPile().pop());
        }
    }
}