package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.UTURN;

/**
 * @author Vivian Kafadar
 * When this card is active, the respective robot should turn 180 degrees to face the opposite direction,
 *      while still remaining on its current space
 */
public class UTurn extends ProgrammingCard {

    public UTurn() {super(UTURN);}

    @Override
    public void discard() {}

    @Override
    public void activateCard() {
        player.getRobot().uTurn();
    }

}