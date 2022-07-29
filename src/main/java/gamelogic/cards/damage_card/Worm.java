package gamelogic.cards.damage_card;

import server_package.Server;

import static gamelogic.cards.CardName.*;

/**
 * @author Qinyi Hui
 * When you program a worm damage card, you must immediately reboot your robot
 */
public class Worm extends DamageCard {

    public Worm(){
        super(WORM);
    }
    public void discard(){
        game.getWormDrawPile().push(this);
    }

    /**
     * @author Mark Ringer
     */
    public void activateCard(){
        player.getRobot().reboot();
        player.clearThisRegister(game.getActiveRegister());
        game.getWormDrawPile().add(this);
        player.placeCard(player.drawCard(), game.getActiveRegister());
        player.activateRegister(game.getActiveRegister());
        Server.serverLogger.info("Worm");
    }

}
