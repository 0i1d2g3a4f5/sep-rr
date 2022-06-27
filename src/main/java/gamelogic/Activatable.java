package gamelogic;

import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.GameElement;

public interface Activatable extends Comparable<GameElement> {
    void activate();
}
