package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.MOVETWO;

public class MoveTwo extends ProgrammingCard {

    public MoveTwo() {super(MOVETWO);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Move your robot two spaces in the direction it is facing.
        player.getRobot().forward();
        player.getRobot().forward();
    }
}