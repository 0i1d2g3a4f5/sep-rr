package gamelogic.cards.special_cards;
import static gamelogic.cards.CardName.*;

public class SandboxRoutine extends SpecialCard {

    public SandboxRoutine(){super(SANDBOX_ROUTINE);}

    public void discard(){

    }

    /** Choose one of the following actions to perform this register:
     * Move1,2,or3,
     * Back Up,
     * Turn Left,
     * Turn Right,
     * U-Turn
     */
    public void PlayCard(){
        switch (CardName){
            case MOVEONE:
                player.getRobot().forward();
                break;
            case MOVETWO:
                player.getRobot().forward();
                player.getRobot().forward();
                break;
            case MOVETHREE:
                player.getRobot().forward();
                player.getRobot().forward();
                player.getRobot().forward();
                break;
            case BACKUP:
                player.getRobot().backward();
                break;
            case LEFTTURN :
                player.getRobot().left();
                break;
            case RIGHTTURN:
                player.getRobot().right();
                break;
            case UTURN:
                player.getRobot().uTurn();
                break;
        }

    }
}
