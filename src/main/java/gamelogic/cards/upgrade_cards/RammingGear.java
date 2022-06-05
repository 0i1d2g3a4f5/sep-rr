package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class RammingGear extends UpgradeCard{

    public RammingGear() {
        super(CardName.RAMMINGGEAR);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: Deal one SPAM damage card when you push a robot.
         */
    }
}
