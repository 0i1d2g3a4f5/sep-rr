package client_package.client_gamelogic.cards.upgrade_cards.temporary;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

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
    }
}
