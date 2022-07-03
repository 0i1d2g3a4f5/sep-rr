package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;

public class MoveThree extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveThree() {super(CardName.MOVE_THREE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward(3);

    }
}