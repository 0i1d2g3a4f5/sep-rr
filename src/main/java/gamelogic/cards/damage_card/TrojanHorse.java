package gamelogic.cards.damage_card;

import gamelogic.Game;
import server_package.Server;

import static gamelogic.cards.CardName.*;

public class TrojanHorse extends DamageCard {

    public TrojanHorse(){
        super(TROJAN_HORSE);
    }

    @Override
    public void discard() {
        game.getTrojanHorseDrawPile().push(this);
    }

    /**
     * @author Mark Ringer
     * if you discard a TrojanHorse, you will immediately get two Spam cards.
     */
    public void activateCard(){
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        player.clearThisRegister(game.getActiveRegister());
        game.getTrojanHorseDrawPile().add(this);
        player.placeCard(player.drawCard(), game.getActiveRegister());
        player.activateRegister(game.getActiveRegister());
        Server.serverLogger.info("Trojan Horse");
    }
}
