package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVE_ONE;

public class MoveOne extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveOne() {super(MOVE_ONE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        System.out.println("Move One");
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward(1);
    }
}
