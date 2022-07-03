package server_package.gamelogic.cards.special_cards;

import server_package.gamelogic.cards.CardName;

public class EnergyRoutine extends SpecialCard {

    public EnergyRoutine(){
        super(CardName.ENERGY_ROUTINE);
    }
    public void discard() {

    }

    /**
     * @author Qinyi
     * the player will take one energy cube.
     */
    public void activateCard(){
        player.addEnergyCube();
    }

}
