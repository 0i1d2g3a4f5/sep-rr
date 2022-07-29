package utility;

import client_package.client_gamelogic.game_elements.Checkpoint;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Ringer
 */
public class SearchMethods {

    /**
     * searches a Card with the CardName in a List
     * @param cardName
     * @param list
     * @return
     * @author Mark Ringer
     */
    public static Card searchCard(CardName cardName, List<Card> list){
        for (Card card:list) {
            if(card.getCardName()==cardName) return card;
        }
        return null;
    }

    /**
     * searches a Card with the CardName in an Array
     *
     * @param cardName
     * @param list
     * @return
     * @author Mark Ringer
     */
    public static Card searchCard(CardName cardName,Card[] list){
        for (Card card:list) {
            if(card.getCardName()==cardName) return card;
        }
        return null;
    }

    /**
     * search Element from ElementName in List
     *
     * @param elementName
     * @param list
     * @return
     * @author Mark Ringer
     */
    public static GameElement searchElement(ElementName elementName, List<GameElement> list){
        for (GameElement element:list) {
            if(element.getType() == elementName) return element;
        }
        return null;
    }

    /**
     * searches Checkpoint with matching id in List of Checkpoints
     * @param ID
     * @param list
     * @return
     * @author Mark Ringer
     */
    public static Checkpoint searchCheckpoint(int ID, ArrayList<Checkpoint> list){
        for (Checkpoint checkpoint:list
             ) {
            if(checkpoint.getCount()==ID){
                return checkpoint;
            }
        }
        return null;
    }

    /**
     * checks, if list of Integers contains all parameters, returns only true if all are found
     *
     * @param list
     * @param params
     * @return
     */
    public static boolean listContainsAll(ArrayList<Integer> list, int... params) {
        for (int param : params) {
            if (!list.contains(param))
                return false;
        }
        return true;
    }
}
