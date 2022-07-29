package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.BACK_UP;

public class BackUp extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public BackUp() {super(BACK_UP);}

    @Override
    public void discard() {

    }
    /**
     * activates the CardEffect
     * @author Ringer
     */
    @Override
    public void activateCard() {
        Server.serverLogger.info("BackUp");
        /*
        Move your robot one space back.
        The robot does not change the direction it is facing.
         */
        player.getRobot().backward(1);
    }
}
