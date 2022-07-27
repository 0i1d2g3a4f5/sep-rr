package gamelogic.cards.damage_card;

import gamelogic.cards.Card;
import messages.MessageReplaceCard;
import server_package.Server;

import static gamelogic.cards.CardName.*;

public class Spam extends DamageCard{

    public Spam(){
        super(SPAM);
    }

    /**
     * @author Mark Ringer, Qinyi
     */
    @Override
    public void discard() {


    }
    /**
     * @author Mark Ringer
     */
    public void activateCard(){
        //TODO notify CPlayer
        player.clearThisRegister(game.getActiveRegister());
        game.getSpamDrawPile().add(this);
        Card card = player.drawCard();
        int activeRegister = game.getActiveRegister();
        player.placeCard(card,activeRegister);
        player.sendMessage(new MessageReplaceCard(activeRegister,card.getCardName(),player.getClient().getId()));
        player.activateRegister(game.getActiveRegister());
        Server.serverLogger.info("Spam");
    }

}
