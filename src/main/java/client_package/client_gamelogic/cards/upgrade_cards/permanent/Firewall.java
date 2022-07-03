package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class Firewall extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public Firewall() {
        super(CardName.FIREWALL);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: Take no SPAM damage cards when rebooting
         */
    }
}
