package client_package.client_gamelogic.cards.special_cards;

import static client_package.client_gamelogic.cards.CardName.SPEED_ROUTINE;

public class SpeedRoutine extends SpecialCard {
    public SpeedRoutine(){
        super(SPEED_ROUTINE);
    }

    @Override
    public void discard() {

    }

    /**
     * @author Qinyi
     * if you discard the speed routine, the robot will move 3 spaces in the direction it's facing
     */
    public void activateCard(){
        player.getRobot().forward(3);

    }
}
