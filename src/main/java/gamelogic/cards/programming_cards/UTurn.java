package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.UTURN;

public class UTurn extends ProgrammingCard {

    public UTurn() {super(UTURN);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.
        player.getRobot().uTurn();
    }

}