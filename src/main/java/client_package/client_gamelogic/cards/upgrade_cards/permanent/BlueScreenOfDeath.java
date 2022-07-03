package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class BlueScreenOfDeath extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public BlueScreenOfDeath() {
        super(CardName.BLUE_SCREEN);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 4
        Effect: When you shoot or push an adjacent robot, you may give that player one worm damage card instead of one SPAM damage card.
         */
    }
}
