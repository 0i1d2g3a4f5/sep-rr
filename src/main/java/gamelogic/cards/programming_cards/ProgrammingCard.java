package gamelogic.cards.programming_cards;

import gamelogic.Game;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;

public abstract class ProgrammingCard extends Card implements PlayableInRegister {
    public ProgrammingCard(CardName cardName) {
        super(Game.getInstance(), cardName);
    }


    @Override
    public void discard(){
    }
}
