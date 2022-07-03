package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class TractorBeam extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public TractorBeam() {super(CardName.TRACTOR_BEAM);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: When you shoot a robot, you may pull it toward you one space.
            Tractor beam may not be used on adjacent robots.
         */
    }
}
