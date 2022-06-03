package gamelogic.Card.SpecialCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Player;

public class EnergyRoutine extends Card {

    public EnergyRoutine(){
        super(CardName.ENERGY_ROUTINE);
    }

    /**
     * @author Qinyi
     * the player will take one energy cube.
     */
    public void PlayCard(){
        player.addEnergyCube();
    }

}
