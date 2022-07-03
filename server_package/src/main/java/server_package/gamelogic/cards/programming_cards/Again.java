package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;

public class
Again extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public Again() {super(CardName.AGAIN);}

    @Override
    public void discard() {

    }

    /**
     * @author Ringer
     */
    @Override
    public void activateCard() {
        //Activates the previous Register
        player.activateRegister(game.getActiveRegister()-1);
    }
}
