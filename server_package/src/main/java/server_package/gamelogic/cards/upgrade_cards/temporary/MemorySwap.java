package server_package.gamelogic.cards.upgrade_cards.temporary;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class MemorySwap extends UpgradeCard {
    public MemorySwap() {
        super(CardName.MEMORY_SWAP);
    }
    public void discard() {

    }

    /**
     * Draw three cards. Then choose three from your hand to put on top of your deck.
     */
    @Override
    public void activateCard() {
        player.getHandCards().add(player.DeckPile().pop());
        player.getHandCards().add(player.DeckPile().pop());
        player.getHandCards().add(player.DeckPile().pop());
        //choose three cards to put on the deck
    }
}
