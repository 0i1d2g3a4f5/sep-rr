package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.TURNRIGHT;

public class RightTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public RightTurn() {super(TURNRIGHT);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Turn your robot 90 degrees to the right. The robot remains in its current space.
        Server.serverLogger.info("Right Turn");
        player.getRobot().right();
    }
}