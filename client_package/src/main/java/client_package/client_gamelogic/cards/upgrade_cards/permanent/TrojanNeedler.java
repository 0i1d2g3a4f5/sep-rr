package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class TrojanNeedler extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public TrojanNeedler() {super(CardName.TROJAN_NEEDLER);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: When you shoot or push a robot, target robot receives damage in the form of one Trojan horse damage card instead of one SPAM damage card.
         */
    }
}
