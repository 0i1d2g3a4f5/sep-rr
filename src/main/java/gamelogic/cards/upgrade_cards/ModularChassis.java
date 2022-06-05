package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class ModularChassis extends UpgradeCard{

    public ModularChassis() {
        super(CardName.MODULAR_CHASSIS);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 1
        Effect: When you push another robot, give that player this card, and take one of their installed upgrades.
            That player keeps this card until they use it.
         */
    }
}
