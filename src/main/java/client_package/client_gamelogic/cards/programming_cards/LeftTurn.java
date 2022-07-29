package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.TURNLEFT;

/**
 * @author Vivian Kafadar
 * When this card is active, the respective robot turns 90 degrees to the left while maintaining its current position
 */
public class LeftTurn extends ProgrammingCard {

    public LeftTurn() {super(TURNLEFT);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        player.getRobot().left();
    }
}
