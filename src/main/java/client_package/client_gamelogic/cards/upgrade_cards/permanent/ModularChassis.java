package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class ModularChassis extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public ModularChassis() {
        super(CardName.MODULAR_CHASSIS);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 1
        Effect: When you push another robot, give that player this card, and take one of their installed upgrades.
            That player keeps this card until they use it.
         */
    }
}
