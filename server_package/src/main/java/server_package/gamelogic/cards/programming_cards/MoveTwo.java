package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;

public class MoveTwo extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveTwo() {super(CardName.MOVE_TWO);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot two spaces in the direction it is facing.
        player.getRobot().forward(2);

    }
}