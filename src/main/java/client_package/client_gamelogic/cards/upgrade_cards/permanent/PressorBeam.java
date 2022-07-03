package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class PressorBeam extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public PressorBeam() {
        super(CardName.PRESSOR_BEAM);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: You may push any robot you shoot one space in the direction you are shooting
         */
    }
}
