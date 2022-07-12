package gamelogic.cards.programming_cards;

import server_package.Server;

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
        System.out.println("Move Two");
        Server.serverLogger.info("Move Two");
        player.getRobot().forward(2);

    }
}