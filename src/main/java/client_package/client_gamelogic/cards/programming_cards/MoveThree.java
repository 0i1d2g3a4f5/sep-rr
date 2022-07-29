package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVEIII;

/**
 * @author Vivian Kafadar
 * When this card is active, the respective robot moves three spaces in the direction it is facing
 */
public class MoveThree extends ProgrammingCard {

    public MoveThree() {super(MOVEIII);}

    @Override
    public void discard() {}

    @Override
    public void activateCard() {
        player.getRobot().forward(3);
    }

}