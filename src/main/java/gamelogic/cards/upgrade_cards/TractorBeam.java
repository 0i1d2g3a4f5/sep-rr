package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class TractorBeam extends UpgradeCard{

    public TractorBeam() {super(CardName.TRACTOR_BEAM);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: When you shoot a robot, you may pull it toward you one space.
            Tractor beam may not be used on adjacent robots.
         */
    }
}
