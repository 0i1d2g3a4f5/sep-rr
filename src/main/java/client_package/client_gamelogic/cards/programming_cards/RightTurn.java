package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.TURNRIGHT;

public class RightTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public RightTurn() {super(TURNRIGHT);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Turn your robot 90 degrees to the right. The robot remains in its current space.
        player.getRobot().right();
    }
}