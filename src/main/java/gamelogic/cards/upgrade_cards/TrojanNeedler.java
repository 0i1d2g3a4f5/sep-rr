package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class TrojanNeedler extends UpgradeCard{

    public TrojanNeedler() {super(CardName.TROJANNEEDLER);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: When you shoot or push a robot, target robot receives damage in the form of one Trojan horse damage card instead of one SPAM damage card.
         */
    }
}
