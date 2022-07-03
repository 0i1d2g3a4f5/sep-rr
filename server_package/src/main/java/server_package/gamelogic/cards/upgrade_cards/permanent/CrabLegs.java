package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class CrabLegs extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public CrabLegs() {
        super(CardName.CRAB_LEGS);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 5
        Effect: When executing a Move 1 card, you may move one space forward then one space right or left,
            without rotating and regardless of the direction you are facing.
            Then move forward one additional space in the direction you are facing.
         */
    }
}
