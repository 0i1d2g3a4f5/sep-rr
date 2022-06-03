package gamelogic.Card.SpecialCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Direction;
import gamelogic.Player;
import gamelogic.robot.Robot;
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
