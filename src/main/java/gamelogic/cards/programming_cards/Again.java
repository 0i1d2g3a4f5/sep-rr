package gamelogic.cards.programming_cards;

import gamelogic.Game;
import gamelogic.cards.Card;
import server_package.Server;

import java.util.Arrays;

import static gamelogic.cards.CardName.AGAIN;

public class
Again extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public Again() {super(AGAIN);}

    @Override
    public void discard() {

    }

    /**
     * @author Ringer
     */
    @Override
    public void activateCard() {

        Server.serverLogger.info("Again");
        //Activates the previous Register
        Card[] register = player.getAllRegisters();

        Server.serverLogger.info("Current Register: " + Arrays.toString(register));

        Server.serverLogger.info("Again activates: " + player.getLastPlayedCard());
        player.getLastPlayedCard().activateCard();
        //player.activateRegister(Game.getInstance().getActiveRegister()-1);
    }
}
