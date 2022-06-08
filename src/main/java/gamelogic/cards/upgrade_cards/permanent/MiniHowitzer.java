package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class MiniHowitzer extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public MiniHowitzer() {
        super(CardName.MINI_HOWITZER);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: Once per register, when you shoot, you may pay one energy to deal two additional SPAM damage cards and
            push the attacked robot one space in the direction you are shooting.
         */
    }
}
