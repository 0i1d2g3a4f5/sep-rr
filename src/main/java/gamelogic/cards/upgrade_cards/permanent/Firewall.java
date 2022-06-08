package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

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
    public void PlayCard() {
        /*
        Cost: 3
        Effect: Take no SPAM damage cards when rebooting
         */
    }
}
