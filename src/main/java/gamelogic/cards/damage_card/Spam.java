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

        Server.serverLogger.debug("Before Spam:");
        for (int i =0;i<player.getAllRegisters().length;i++) {
            if(player.getAllRegisters()[i]!=null){
                if(player.getAllRegisters()[i].getCardName()==SPAM) {
                    Spam spam = (Spam) player.getAllRegisters()[i];
                    player.getAllRegisters()[i] = null;
                    Card card = player.drawCard();
                    player.placeCard(card, i);
                    player.sendMessage(new MessageReplaceCard(i, card.getCardName(), player.getClient().getId()));
                    player.activateRegister(i);
                }
            }
        }

        /*
        player.clearThisRegister(game.getActiveRegister());
        game.getSpamDrawPile().add(this);

        int activeRegister = game.getActiveRegister();




         */
        Server.serverLogger.info("Spam");
    }

}
