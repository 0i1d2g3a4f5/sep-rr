package gamelogic.Card.special_cards;

import gamelogic.Card.Card;

import static gamelogic.Card.CardName.*;

public class SpeedRoutine extends Card {
    public SpeedRoutine(){
        super(SPEED_ROUTINE);
    }

    /**
     * @author Qinyi
     * if you discard the speed routine, the robot will move 3 spaces in the direction it's facing
     */
    public void PlayCard(){
        player.getRobot().forward();
        player.getRobot().forward();
        player.getRobot().forward();
    }
}
