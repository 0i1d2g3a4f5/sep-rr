package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.RIGHTTURN;

public class RightTurn extends ProgrammingCard {

    public RightTurn() {super(RIGHTTURN);}

    @Override
    public void PlayCard() {
        // Turn your robot 90 degrees to the right. The robot remains in its current space.
    }
}