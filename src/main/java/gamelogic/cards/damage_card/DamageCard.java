package gamelogic.cards.damage_card;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.playableInRegister;

public abstract class DamageCard extends Card implements playableInRegister {
    public DamageCard(CardName cardName) {
        super(cardName);
    }
}
