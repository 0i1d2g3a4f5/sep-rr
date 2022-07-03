package server_package.gamelogic.cards.special_cards;

import server_package.gamelogic.cards.CardName;

public class SpeedRoutine extends SpecialCard {
    public SpeedRoutine(){
        super(CardName.SPEED_ROUTINE);
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
