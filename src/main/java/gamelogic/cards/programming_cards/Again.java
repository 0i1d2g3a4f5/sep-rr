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
     * activates the CardEffect
     * @author Ringer
     */
    @Override
    public void activateCard() {

        Server.serverLogger.info("Again");
        //Activates the previous Register
        Card[] register = player.getAllRegisters();

        Server.serverLogger.info("Current Register: " + Arrays.toString(register));

        Server.serverLogger.info("Again activates: " + player.getLastPlayedCard());
        if(player.getLastPlayedCard()!=null && player.getLastPlayedCard().getCardName()!=AGAIN)
            player.getLastPlayedCard().activateCard();
        else{
            Server.serverLogger.error("Card Again could not be activated");
        }
    }
}
