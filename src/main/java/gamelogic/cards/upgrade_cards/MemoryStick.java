package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class MemoryStick extends UpgradeCard{

    public MemoryStick() {
        super(CardName.MEMORYSTICK);
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
