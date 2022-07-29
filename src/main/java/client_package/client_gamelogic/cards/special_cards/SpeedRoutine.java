package client_package.client_gamelogic.cards.special_cards;

import static client_package.client_gamelogic.cards.CardName.SPEED_ROUTINE;

/**
 * @author Qinyi Hui
 * When this card gets discarded, the respective robot will move three spaces in the direction it's facing
 */
public class SpeedRoutine extends SpecialCard {
    public SpeedRoutine(){
        super(SPEED_ROUTINE);
    }

    @Override
    public void discard() {}

    public void activateCard(){}
}
