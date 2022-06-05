package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.POWERUP;

public class PowerUp extends ProgrammingCard {

    public PowerUp() {super(POWERUP);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Take one energy cube, and place it on your player mat.
    }
}