package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class MemoryStick extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public MemoryStick() {
        super(CardName.MEMORY_STICK);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: Draw one additional programming card at the start of each round.
         */
    }
}
