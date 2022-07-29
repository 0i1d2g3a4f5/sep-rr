package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.MOVEII;

/**
 * @author Vivian Kafadar
 * When this card is active, the respective robot moves two spaces in the direction it is facing
 */
public class MoveTwo extends ProgrammingCard {

    public MoveTwo() {super(MOVEII);}

    @Override
    public void discard() {}

    @Override
    public void activateCard() {
        player.getRobot().forward(2);
    }

}