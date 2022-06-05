package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class PressorBeam extends UpgradeCard {

    public PressorBeam() {
        super(CardName.PRESSOR_BEAM);
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
