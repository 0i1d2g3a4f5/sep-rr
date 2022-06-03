package gamelogic.Card.SpecialCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Player;
import static gamelogic.Card.CardName.*;

public class EnergyRoutine extends Card {

    public EnergyRoutine(){
        super(ENERGY_ROUTINE);
    }

    /**
     * @author Qinyi
     * the player will take one energy cube.
     */
    public void PlayCard(){
        player.addEnergyCube();
    }

}
