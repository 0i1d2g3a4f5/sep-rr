package client_package.client_gamelogic.cards.damage_card;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.PlayableInRegister;

public abstract class DamageCard extends Card implements PlayableInRegister {
    public DamageCard(CardName cardName) {
        super(cardName);
    }
}
