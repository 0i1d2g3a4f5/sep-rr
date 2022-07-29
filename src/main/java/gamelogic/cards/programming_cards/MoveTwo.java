package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.MOVEII;

public class MoveTwo extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveTwo() {super(MOVEII);}

    @Override
    public void discard() {

    }
    /**
     * activates the CardEffect
     * @author Ringer
     */
    @Override
    public void activateCard() {
        // Move your robot two spaces in the direction it is facing.
        Server.serverLogger.info("Move Two");
        player.getRobot().forward(2);

    }
}