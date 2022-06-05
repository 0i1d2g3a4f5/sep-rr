package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class PressorBeam extends UpgradeCard{

    public PressorBeam() {
        super(CardName.PRESSORBEAM);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: You may push any robot you shoot one space in the direction you are shooting
         */
    }
}
