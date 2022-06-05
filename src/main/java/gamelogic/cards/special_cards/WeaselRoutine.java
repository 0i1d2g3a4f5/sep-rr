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
        switch (CardName){
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
