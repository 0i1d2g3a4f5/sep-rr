package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class BlueScreenOfDeath extends UpgradeCard {

    public BlueScreenOfDeath() {
        super(CardName.BLUE_SCREEN);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 4
        Effect: When you shoot or push an adjacent robot, you may give that player one worm damage card instead of one SPAM damage card.
         */
    }
}
