package client_package.client_gamelogic.cards.programming_cards;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.PlayableInRegister;

public abstract class ProgrammingCard extends Card implements PlayableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(cardName);
    }
}
