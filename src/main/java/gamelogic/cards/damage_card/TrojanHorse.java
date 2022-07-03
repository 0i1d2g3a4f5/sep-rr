package gamelogic.cards.damage_card;

import gamelogic.Game;

import static gamelogic.cards.CardName.*;

public class TrojanHorse extends DamageCard {

    public TrojanHorse(){
        super(TROJAN_HORSE);
    }

    @Override
    public void discard() {
        //TODO check game rules
        game.getTrojanHorseDrawPile().push(this);
        /*
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().push());
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().push());
         */
    }

    /**
     *
     * if you discard a TrojanHorse, you will immediately get two Spam cards.
     */
    public void activateCard(){
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        //TODO notify Player
        player.clearThisRegister(game.getActiveRegister());
        game.getTrojanHorseDrawPile().add(this);
        player.placeCard(player.drawCard(), game.getActiveRegister());
        player.activateRegister(game.getActiveRegister());
        //remove card from register and replace it with a new one.
    }
}