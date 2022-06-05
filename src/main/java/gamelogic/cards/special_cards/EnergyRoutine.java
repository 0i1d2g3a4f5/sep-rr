package gamelogic.cards.special_cards;

import static gamelogic.cards.CardName.*;

public class EnergyRoutine extends SpecialCard {

    public EnergyRoutine(){
        super(ENERGY_ROUTINE);
    }
    public void discard() {

    }

    /**
     * @author Qinyi
     * the player will take one energy cube.
     */
    public void PlayCard(){
        player.addEnergyCube();
    }

}
