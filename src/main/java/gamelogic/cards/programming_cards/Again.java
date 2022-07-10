package gamelogic.cards.programming_cards;

import gamelogic.Game;
import gamelogic.cards.Card;

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
        System.out.println("Again");
        //Activates the previous Register
        Card[] register = player.getAllRegisters();
        System.out.println("Again activates: " +register[Game.getInstance().getActiveRegister()-1]);
        register[Game.getInstance().getActiveRegister()-1].activateCard();
        //player.activateRegister(Game.getInstance().getActiveRegister()-1);
    }
}
