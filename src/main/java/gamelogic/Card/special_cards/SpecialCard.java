package gamelogic.Card.special_cards;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Card.playableInRegister;

public abstract class SpecialCard extends Card<P> implements playableInRegister {
    public SpecialCard(CardName cardName){
        super(cardName);
    }

}
