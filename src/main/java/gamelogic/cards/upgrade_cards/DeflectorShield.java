package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class DeflectorShield extends UpgradeCard {

    public DeflectorShield() {
        super(CardName.DEFLECTORSHIELD);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: At the beginning of a register, you may spend one energy to negate any robot lasers that would hit you that register.
         */
    }
}
