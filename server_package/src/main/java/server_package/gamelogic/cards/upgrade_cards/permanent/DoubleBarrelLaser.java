package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class DoubleBarrelLaser extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public DoubleBarrelLaser() {
            super(CardName.DOUBLE_BARREL_LASER);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: Deal one additional SPAM damage card to any robot you shoot
         */
    }
}
