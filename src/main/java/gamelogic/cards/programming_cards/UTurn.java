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
        System.out.println("uTurn");
        Server.serverLogger.info("U-Turn");
        // Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.
        player.getRobot().uTurn();
    }

}