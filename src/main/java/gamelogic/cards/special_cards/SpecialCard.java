package gamelogic.cards.special_cards;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.playableInRegister;

public abstract class SpecialCard extends Card implements playableInRegister {
    public SpecialCard(CardName cardName){
        super(cardName);
    }

}
