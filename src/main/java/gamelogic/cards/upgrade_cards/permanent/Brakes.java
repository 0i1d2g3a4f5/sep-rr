package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class Brakes extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public Brakes() {
        super(CardName.BRAKES);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: You may treat your Move 1’s as Move 0’s.
         */
    }
}
