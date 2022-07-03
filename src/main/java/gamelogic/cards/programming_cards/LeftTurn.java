package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.LEFT_TURN;

public class LeftTurn extends ProgrammingCard{

    /**
     * @author Kafadar
     */

    public LeftTurn() {super(LEFT_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Turn your robot 90 degrees to the left.
        The robot remains in its current space.
         */
        player.getRobot().left();
    }
}