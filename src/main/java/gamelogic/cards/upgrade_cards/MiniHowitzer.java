package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class MiniHowitzer extends UpgradeCard{

    public MiniHowitzer() {
        super(CardName.MINIHOWITZER);
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
