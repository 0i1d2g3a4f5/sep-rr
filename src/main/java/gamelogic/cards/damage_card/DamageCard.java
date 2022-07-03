package gamelogic.cards.damage_card;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;

public abstract class DamageCard extends Card implements PlayableInRegister {
    public DamageCard(CardName cardName) {
        super(cardName);
    }
}
