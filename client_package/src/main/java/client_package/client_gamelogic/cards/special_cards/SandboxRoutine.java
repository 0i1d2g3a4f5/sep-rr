package client_package.client_gamelogic.cards.special_cards;


import static client_package.client_gamelogic.cards.CardName.SANDBOX_ROUTINE;

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
            case MOVE_ONE:
                player.getRobot().forward(1);
                break;
            case MOVE_TWO:
                player.getRobot().forward(2);

                break;
            case MOVE_THREE:
                player.getRobot().forward(3);
                break;
            case BACK_UP:
                player.getRobot().backward(1);
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
