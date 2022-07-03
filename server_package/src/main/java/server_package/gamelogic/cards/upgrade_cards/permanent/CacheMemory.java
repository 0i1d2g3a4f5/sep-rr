package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class CacheMemory extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public CacheMemory() {
        super(CardName.CACHE_MEMORY);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 4
        Effect: You may discard cards from your hand and place them on the top of your deck.
            Do not draw replacement cards.
         */
    }
}
