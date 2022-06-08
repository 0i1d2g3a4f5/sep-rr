package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.U_TURN;

public class UTurn extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public UTurn() {super(U_TURN);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        // Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.
        player.getRobot().uTurn();
    }

}