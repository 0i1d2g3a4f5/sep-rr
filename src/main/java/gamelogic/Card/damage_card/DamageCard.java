package gamelogic.Card.damage_card;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Card.playableInRegister;

public abstract class DamageCard extends Card implements playableInRegister {
    public DamageCard(CardName cardName) {
        super(cardName);
    }
}
