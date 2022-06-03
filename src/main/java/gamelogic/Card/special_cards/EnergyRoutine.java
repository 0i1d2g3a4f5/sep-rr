package gamelogic.Card.special_cards;

import gamelogic.Card.Card;

import static gamelogic.Card.CardName.*;

public class EnergyRoutine extends SpecialCard {

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
