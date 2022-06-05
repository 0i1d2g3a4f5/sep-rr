package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class Firewall extends UpgradeCard{

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
