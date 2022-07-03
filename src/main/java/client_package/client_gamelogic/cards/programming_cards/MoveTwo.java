package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVE_TWO;

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
        player.getRobot().forward(2);

    }
}