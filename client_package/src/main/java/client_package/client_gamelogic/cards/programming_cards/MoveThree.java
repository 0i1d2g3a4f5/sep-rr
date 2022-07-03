package client_package.client_gamelogic.cards.programming_cards;


import static client_package.client_gamelogic.cards.CardName.MOVE_THREE;

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
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward(3);

    }
}