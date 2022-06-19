package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.POWER_UP;

public class PowerUp extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public PowerUp() {super(POWER_UP);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Take one energy cube, and place it on your player mat.
    }
}