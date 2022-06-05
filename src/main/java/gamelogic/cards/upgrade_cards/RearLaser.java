package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class RearLaser extends UpgradeCard{

    public RearLaser() {super(CardName.REARLASER);}

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
