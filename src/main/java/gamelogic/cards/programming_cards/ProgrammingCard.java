package gamelogic.cards.programming_cards;

import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;

public abstract class ProgrammingCard extends Card implements PlayableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(cardName);
    }

    @Override
    public void discard(){
        //TODO Check if needed
    }
}
