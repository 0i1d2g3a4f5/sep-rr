package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

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
