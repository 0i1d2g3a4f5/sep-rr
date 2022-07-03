package client_package.client_gamelogic.cards.special_cards;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;

import java.util.Stack;



public class SpamFolder extends SpecialCard {
    public SpamFolder(){
        super(CardName.SPAM_FOLDER);
    }
    public void discard() {

    }

    /**
     * @author Qinyi
     * Permanently discard an SPAM damage card from your discard pile to the SPAM damage card draw pile.
     */
    @Override
    public void activateCard(){
    }
}
