package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;

public class RightTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public RightTurn() {super(CardName.RIGHT_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Turn your robot 90 degrees to the right. The robot remains in its current space.
        player.getRobot().right();
    }
}