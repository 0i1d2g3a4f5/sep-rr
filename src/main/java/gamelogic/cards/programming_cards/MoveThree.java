package gamelogic.cards.programming_cards;

import gamelogic.cards.CardName;
import server_package.Server;


public class MoveThree extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveThree() {super(CardName.MOVEIII);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        Server.serverLogger.info("Move Three");
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward(3);

    }
}