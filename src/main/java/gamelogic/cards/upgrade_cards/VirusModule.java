package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class VirusModule extends UpgradeCard{

    public VirusModule() {super(CardName.VIRUSMODULE);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: When you push a robot, give that player a virus damage card.
         */
    }
}
