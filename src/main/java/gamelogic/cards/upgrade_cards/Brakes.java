package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class Brakes extends UpgradeCard {

    public Brakes() {
        super(CardName.BRAKES);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: You may treat your Move 1’s as Move 0’s.
         */
    }
}
