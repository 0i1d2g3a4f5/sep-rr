package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class AdminPrivilege extends UpgradeCard {

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
