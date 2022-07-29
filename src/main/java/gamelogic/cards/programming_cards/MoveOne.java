package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.MOVEI;

public class MoveOne extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveOne() {super(MOVEI);}

    @Override
    public void discard() {

    }
    /**
     * activates the CardEffect
     * @author Ringer
     */
    @Override
    public void activateCard() {
        Server.serverLogger.info("Move One");
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward(1);
    }
}
