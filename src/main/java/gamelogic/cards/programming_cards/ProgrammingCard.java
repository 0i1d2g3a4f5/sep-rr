package gamelogic.cards.programming_cards;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.playableInRegister;

public abstract class ProgrammingCard extends Card implements playableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(cardName);
    }
}
