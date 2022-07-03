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


    }

    public void activateCard(){
        //TODO notify Player
        player.clearThisRegister(game.getActiveRegister());
        game.getSpamDrawPile().add(this);
        player.placeCard(player.drawCard(), game.getActiveRegister());
        player.activateRegister(game.getActiveRegister());

    }

}
