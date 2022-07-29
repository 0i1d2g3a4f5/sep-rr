package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVEI;

public class MoveOne extends ProgrammingCard {

    public MoveOne() {super(MOVEI);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward(1);
    }
}
