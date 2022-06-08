package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.RIGHT_TURN;

public class RightTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public RightTurn() {super(RIGHT_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Turn your robot 90 degrees to the right. The robot remains in its current space.
        player.getRobot().right();
    }
}