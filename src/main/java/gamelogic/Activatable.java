package gamelogic;

import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;
import gamelogic.map.GameField;

public interface Activatable extends Comparable<Activatable> {


    int getActivationOrder();
    void activate();

    GameField getGameField();

    String toString();
}
