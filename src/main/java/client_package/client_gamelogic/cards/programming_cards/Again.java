package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.AGAIN;

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
        //Activates the previous Register
        player.activateRegister(game.getActiveRegister()-1);
    }
}
