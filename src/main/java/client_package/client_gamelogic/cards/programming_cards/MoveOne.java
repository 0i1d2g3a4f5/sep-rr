package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVEI;

/**
 * @author Vivian Kafadar
 * When this card is active, the respective robot moves one space in the direction it is facing
 */
public class MoveOne extends ProgrammingCard {

    public MoveOne() {super(MOVEI);}

    @Override
    public void discard() {}

    @Override
    public void activateCard() {
        player.getRobot().forward(1);
    }
}
