package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class CrabLegs extends UpgradeCard {

    public CrabLegs() {
        super(CardName.CRAB_LEGS);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 5
        Effect: When executing a Move 1 card, you may move one space forward then one space right or left,
            without rotating and regardless of the direction you are facing.
            Then move forward one additional space in the direction you are facing.
         */
    }
}
