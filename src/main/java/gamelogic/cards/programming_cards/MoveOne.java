package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVEONE;

public class MoveOne extends ProgrammingCard {

    public MoveOne() {super(MOVEONE);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward();
    }
}
