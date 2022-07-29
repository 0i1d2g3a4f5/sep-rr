package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.U_TURN;

public class UTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public UTurn() {super(U_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        Server.serverLogger.info("U-Turn");
        player.getRobot().uTurn();
    }

}