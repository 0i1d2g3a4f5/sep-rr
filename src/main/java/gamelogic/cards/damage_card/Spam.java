package gamelogic.cards.damage_card;

import gamelogic.Game;
import gamelogic.cards.Card;
import gamelogic.cards.CardName.*;

import static gamelogic.cards.CardName.*;

public class Spam extends DamageCard{

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
