package client_package.client_gamelogic.cards.damage_card;

import static client_package.client_gamelogic.cards.CardName.VIRUS;

public class Virus extends DamageCard {

    /**
     * @author Qinyi
     * When you program a virus damage card, any robot on the board within a six-space radius of you
     * must immediately take a virus card from the draw pile.
     */
    public Virus(){
        super(VIRUS);
    }
    public void discard(){
        game.getVirusDrawPile().push(this);
    }

    public void activateCard(){
        //TODO: should add a method in Game/Player class to calculate the distance between current player and other players

    }
}
