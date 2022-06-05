package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class CacheMemory extends UpgradeCard {

    public CacheMemory() {
        super(CardName.CACHE_MEMORY);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 4
        Effect: You may discard cards from your hand and place them on the top of your deck.
            Do not draw replacement cards.
         */
    }
}
