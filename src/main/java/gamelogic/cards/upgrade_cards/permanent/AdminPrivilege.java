package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class AdminPrivilege extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public AdminPrivilege() {
        super(CardName.ADMIN_PRIVILEGE);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: Once per round, you may give your robot priority for one register.
         */
    }
}
