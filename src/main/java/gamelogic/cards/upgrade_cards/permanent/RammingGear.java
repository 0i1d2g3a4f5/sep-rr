package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class RammingGear extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public RammingGear() {
        super(CardName.RAMMING_GEAR);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: Deal one SPAM damage card when you push a robot.
         */
    }
}
