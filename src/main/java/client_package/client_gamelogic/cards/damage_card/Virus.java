package client_package.client_gamelogic.cards.damage_card;

import static client_package.client_gamelogic.cards.CardName.VIRUS;

/**
 * @author Qinyi Hui
 * When you program a virus damage card, any robot on the board within a six-space radius of you
 * must immediately take a virus card from the draw pile.
 */
public class Virus extends DamageCard {

    public Virus(){
        super(VIRUS);
    }
    public void discard(){

    }

    public void activateCard(){
    }
}
