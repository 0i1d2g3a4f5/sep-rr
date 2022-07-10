package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVE_THREE;

public class MoveThree extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveThree() {super(MOVE_THREE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        System.out.println("Move Three");
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward(3);

    }
}