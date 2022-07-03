package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.PlayableInRegister;

public abstract class ProgrammingCard extends Card implements PlayableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(cardName);
    }
}
