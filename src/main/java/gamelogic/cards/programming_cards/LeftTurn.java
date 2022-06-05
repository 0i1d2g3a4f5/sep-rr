package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.LEFT_TURN;

public class LeftTurn extends ProgrammingCard{

    public LeftTurn() {super(LEFT_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Turn your robot 90 degrees to the left.
        The robot remains in its current space.
         */
        player.getRobot().left();
    }
}
