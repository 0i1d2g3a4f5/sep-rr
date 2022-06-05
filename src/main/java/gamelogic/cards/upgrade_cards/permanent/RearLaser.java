package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class RearLaser extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public RearLaser() {super(CardName.REAR_LASER);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: Your robot may shoot backward as well as forward.
         */
    }
}
