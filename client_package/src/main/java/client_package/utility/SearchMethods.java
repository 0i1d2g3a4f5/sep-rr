package client_package.utility;

import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.game_elements.ElementName;
import server_package.gamelogic.game_elements.GameElement;

import java.util.List;

/**
 * @author Ringer
 */
public class SearchMethods {
    /**
     * @author Ringer
     * @param cardName
     * @param list
     * @return
     */
    public static Card searchCard(CardName cardName, List<Card> list){
        for (Card card:list) {
            if(card.getCardName()==cardName) return card;
        }
        return null;
    }

    /**
     * @author Ringer
     * @param elementName
     * @param list
     * @return
     */
    public static GameElement searchElement(ElementName elementName, List<GameElement> list){
        for (GameElement element:list) {
            if(element.getType() == elementName) return element;
        }
        return null;
    }
}
