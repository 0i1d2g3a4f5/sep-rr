package client_package.client_gamelogic.cards.damage_card;

import static client_package.client_gamelogic.cards.CardName.SPAM;

public class Spam extends DamageCard {

    public Spam(){
        super(SPAM);
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
