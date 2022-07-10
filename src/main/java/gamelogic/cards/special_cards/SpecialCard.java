package gamelogic.cards.special_cards;

import gamelogic.Game;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;

public abstract class SpecialCard extends Card implements PlayableInRegister {
    public SpecialCard(CardName cardName){
        super(Game.getInstance(), cardName);
    }

}
