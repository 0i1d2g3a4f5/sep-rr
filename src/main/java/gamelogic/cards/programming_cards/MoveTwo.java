package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVE_TWO;

public class MoveTwo extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveTwo() {super(MOVE_TWO);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot two spaces in the direction it is facing.
        System.out.println("move Two");
        player.getRobot().forward(2);

    }
}