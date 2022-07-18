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
        //TODO player message, player choose a card
        switch (cardName){
            case MOVEI:
                player.getRobot().forward(1);
                break;
            case MOVEII:
                player.getRobot().forward(2);

                break;
            case MOVEIII:
                player.getRobot().forward(3);
                break;
            case BACK_UP:
                player.getRobot().backward(1);
                break;
            case TURNLEFT:
                player.getRobot().left();
                break;
            case TURNRIGHT:
                player.getRobot().right();
                break;
            case U_TURN:
                player.getRobot().uTurn();
                break;
        }

    }
}
