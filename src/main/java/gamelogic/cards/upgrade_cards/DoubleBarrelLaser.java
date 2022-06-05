package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class DoubleBarrelLaser extends UpgradeCard {

    public DoubleBarrelLaser() {
            super(CardName.DOUBLEBARRELLASER);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: Deal one additional SPAM damage card to any robot you shoot
         */
    }
}
