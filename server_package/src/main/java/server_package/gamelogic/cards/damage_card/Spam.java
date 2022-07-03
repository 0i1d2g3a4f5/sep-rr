package server_package.gamelogic.cards.damage_card;

import server_package.gamelogic.cards.CardName;

public class Spam extends DamageCard{

    public Spam(){
        super(CardName.SPAM);
    }

    /**
     * @author Qinyi
     */
    @Override
    public void discard() {
        game.getSpamDrawPile().push(this);

    }

    public void activateCard(){
    }

}
