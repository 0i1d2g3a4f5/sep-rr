package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.TURNLEFT;

public class LeftTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public LeftTurn() {super(TURNLEFT);}

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
