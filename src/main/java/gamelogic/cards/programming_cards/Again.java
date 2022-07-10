package gamelogic.cards.programming_cards;

import gamelogic.Game;

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
        player.activateRegister(Game.getInstance().getActiveRegister()-1);
    }
}
