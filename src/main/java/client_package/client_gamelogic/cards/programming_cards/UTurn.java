package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.UTURN;

public class UTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public UTurn() {super(UTURN);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.
        player.getRobot().uTurn();
    }

}