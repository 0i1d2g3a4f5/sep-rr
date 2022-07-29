package client_package.client_gamelogic.cards.special_cards;

import static client_package.client_gamelogic.cards.CardName.SANDBOX_ROUTINE;

/**
 * @author Qinyi Hui
 * When this card is discarded, the player has to choose on of the following actions:
 *      Move 1, 2, 3, Back Up, Turn Left, Turn Right, U-Turn
 */
public class SandboxRoutine extends SpecialCard {

    public SandboxRoutine(){super(SANDBOX_ROUTINE);}

    public void discard(){}

    public void activateCard(){
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
            case UTURN:
                player.getRobot().uTurn();
                break;
        }
    }
}
