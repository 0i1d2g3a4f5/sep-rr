package utility;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;

import java.util.List;

public class CardUtility {
    public static Card searchCard(CardName cardName, List<Card> list){
        for (Card card:list) {
            if(card.getCardName()==cardName) return card;
        }
        return null;
    }
}
