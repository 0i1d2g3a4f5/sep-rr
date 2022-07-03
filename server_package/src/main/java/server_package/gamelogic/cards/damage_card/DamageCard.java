package server_package.gamelogic.cards.damage_card;

import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.PlayableInRegister;

public abstract class DamageCard extends Card implements PlayableInRegister {
    public DamageCard(CardName cardName) {
        super(cardName);
    }
}
