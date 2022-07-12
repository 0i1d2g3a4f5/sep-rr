package gamelogic.cards.damage_card;

import gamelogic.Game;
import gamelogic.Player;
import server_package.Server;
import utility.MyMath;

import static gamelogic.cards.CardName.*;
import static utility.MyMath.pythagoras;

public class Virus extends DamageCard {

    /**
     * @author Qinyi
     * When you program a virus damage card, any robot on the board within a six-space radius of you
     * must immediately take a virus card from the draw pile.
     */
    public Virus(){
        super(VIRUS);
    }
    public void discard(){
        game.getVirusDrawPile().push(this);
    }

    /**
     * @author Mark Ringer
     */
    public void activateCard(){
        for (Player player:game.getPlayerList()) {
            if(pythagoras(player.getRobot().getPosition(),this.player.getRobot().getPosition())>=6 ){
                player.drawVirus(1);
            }

        }
        //TODO notify CPlayer
        player.clearThisRegister(game.getActiveRegister());
        game.getVirusDrawPile().add(this);
        player.placeCard(player.drawCard(), game.getActiveRegister());
        player.activateRegister(game.getActiveRegister());
        Server.serverLogger.info("Virus");

    }
}
