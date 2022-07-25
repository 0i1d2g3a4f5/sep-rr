package utility;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ringer
 */
public class SearchMethods {

    public static int emptyArraySpaces(Object[] array){
        int emptySpaces = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null){
                emptySpaces++;
            }
        }
        return emptySpaces;
    }
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

    public static Card searchCard(CardName cardName,Card[] list){
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

    public static boolean listContainsAll(ArrayList<Integer> list, int... params) {
        for (int param : params) {
            if (!list.contains(param))
                return false;
        }
        return true;
    }
}
