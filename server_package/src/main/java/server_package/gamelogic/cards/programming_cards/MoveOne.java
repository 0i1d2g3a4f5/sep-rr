package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;

public class MoveOne extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveOne() {super(CardName.MOVE_ONE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward(1);
    }
}
