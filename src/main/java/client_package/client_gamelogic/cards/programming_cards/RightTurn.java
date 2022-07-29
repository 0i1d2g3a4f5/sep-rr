package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.TURNRIGHT;

/**
 * @author Vivian Kafadar
 * When the card is active, the respective robot should turn 90 degrees to the right while remaining on its current space
 */
public class RightTurn extends ProgrammingCard {

    public RightTurn() {super(TURNRIGHT);}

    @Override
    public void discard() {}

    @Override
    public void activateCard() {
        player.getRobot().right();
    }

}