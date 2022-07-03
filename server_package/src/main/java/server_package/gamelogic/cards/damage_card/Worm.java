package server_package.gamelogic.cards.damage_card;

import server_package.gamelogic.cards.CardName;

/**
 * @author Qinyi
 * When you program a worm damage card, you must immediately reboot your robot
 */
public class Worm extends DamageCard {

    public Worm(){
        super(CardName.WORM);
    }
    public void discard(){
        game.getWormDrawPile().push(this);
    }

    public void activateCard(){
    }

}
