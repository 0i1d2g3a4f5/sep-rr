package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVE_THREE;

public class MoveThree extends ProgrammingCard {

    public MoveThree() {super(MOVE_THREE);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Move your robot three spaces in the direction it is facing.
        player.getRobot().forward();
        player.getRobot().forward();
        player.getRobot().forward();
    }
}