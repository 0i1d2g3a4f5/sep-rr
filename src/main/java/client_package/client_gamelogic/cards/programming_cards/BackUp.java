package client_package.client_gamelogic.cards.programming_cards;

import static client_package.client_gamelogic.cards.CardName.BACK_UP;

/**
 * @author Vivian Kafadar
 * When the Back Up card is active, the respective robot moves one space back without changing the direction its facing
 */
public class BackUp extends ProgrammingCard {

    public BackUp() {super(BACK_UP);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        player.getRobot().backward(1);
    }
}
