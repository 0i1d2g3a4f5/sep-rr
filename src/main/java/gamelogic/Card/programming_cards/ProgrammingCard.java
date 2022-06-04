package gamelogic.Card.programming_cards;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Card.playableInRegister;

public abstract class ProgrammingCard extends Card implements playableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(cardName);
    }
}
