package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class RearLaser extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public RearLaser() {super(CardName.REAR_LASER);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: Your robot may shoot backward as well as forward.
         */
    }
}
