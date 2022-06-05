package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.POWER_UP;

public class PowerUp extends ProgrammingCard {

    /**
     * @auther Kafadar
     */

    public PowerUp() {super(POWER_UP);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Take one energy cube, and place it on your player mat.
    }
}