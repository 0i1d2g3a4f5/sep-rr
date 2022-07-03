package client_package.client_gamelogic.cards.special_cards;


import static client_package.client_gamelogic.cards.CardName.WEASEL_ROUTINE;

public class WeaselRoutine extends SpecialCard {

    public WeaselRoutine(){super(WEASEL_ROUTINE);}

    public void discard(){

    }

    /** Choose one of the following actions to perform this register:
     * Turn Left,
     * Turn Right,
     * U-Turn
     */
    public void activateCard(){
        //TODO PlayerMessage, player choose a card
        switch (cardName){
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
