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
    public void activateCard(){
        //player choose a card
        switch (cardName){
            case MOVE_ONE:
                player.getRobot().forward();
                break;
            case MOVE_TWO:
                player.getRobot().forward();
                player.getRobot().forward();
                break;
            case MOVE_THREE:
                player.getRobot().forward();
                player.getRobot().forward();
                player.getRobot().forward();
                break;
            case BACK_UP:
                player.getRobot().backward();
                break;
            case LEFT_TURN :
                player.getRobot().left();
                break;
            case RIGHT_TURN:
                player.getRobot().right();
                break;
            case U_TURN:
                player.getRobot().uTurn();
                break;
        }

    }
}
