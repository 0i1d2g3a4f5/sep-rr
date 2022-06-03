package gamelogic.Card.SpecialCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Player;

import java.util.Stack;

public class SpamFolder extends Card {
    public SpamFolder(){
        super(CardName.SPAM_FOLDER);
    }

    /**
     * @author Qinyi
     * Permanently discard an SPAM damage card from your discard pile to the SPAM damage card draw pile.
     */
    @Override
    public void PlayCard(){
        Stack<Card> discardPile =player.getDiscardPile();
        for (Card card:discardPile) {
            if(card.getCardName()==CardName.SPAM) discardPile.remove(card);
        }
    }
}
