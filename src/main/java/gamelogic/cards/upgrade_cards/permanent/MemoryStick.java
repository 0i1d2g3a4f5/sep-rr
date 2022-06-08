package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

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
    public void PlayCard() {
        /*
        Cost: 3
        Effect: Draw one additional programming card at the start of each round.
         */
    }
}
