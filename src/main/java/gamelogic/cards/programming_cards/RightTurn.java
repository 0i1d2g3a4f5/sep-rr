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
        Server.serverLogger.info("Right Turn");
        player.getRobot().right();
    }
}