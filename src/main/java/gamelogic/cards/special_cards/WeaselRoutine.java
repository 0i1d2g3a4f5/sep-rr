package gamelogic.cards.special_cards;

import gamelogic.cards.CardName.*;

import static gamelogic.cards.CardName.*;

public class WeaselRoutine extends SpecialCard {

    public WeaselRoutine(){super(SANDBOX_ROUTINE);}

    public void discard(){

    }

    /** Choose one of the following actions to perform this register:
     * Turn Left,
     * Turn Right,
     * U-Turn
     */
    public void PlayCard(){
        //player choose a card
        switch (CardName){
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
