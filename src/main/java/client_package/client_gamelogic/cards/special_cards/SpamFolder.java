package client_package.client_gamelogic.cards.special_cards;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;

import java.util.Stack;

/**
 * @author Qinyi Hui
 * When this card is discarded, the player permanently discards a spam damage card from their discard pile
 *      to the spam damage card draw pile
 */
public class SpamFolder extends SpecialCard {
    public SpamFolder(){
        super(CardName.SPAM_FOLDER);
    }
    public void discard() {

    }

    @Override
    public void activateCard(){
    }
}
