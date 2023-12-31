package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class DeflectorShield extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public DeflectorShield() {
        super(CardName.DEFLECTOR_SHIELD);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: At the beginning of a register, you may spend one energy to negate any robot lasers that would hit you that register.
         */
    }
}
