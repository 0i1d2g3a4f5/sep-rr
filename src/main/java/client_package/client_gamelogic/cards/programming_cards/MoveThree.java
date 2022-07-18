package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVEIII;

public class MoveThree extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveThree() {super(MOVEIII);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward(3);

    }
}