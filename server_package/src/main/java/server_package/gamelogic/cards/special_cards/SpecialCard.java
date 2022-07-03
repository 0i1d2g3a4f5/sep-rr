package server_package.gamelogic.cards.special_cards;

import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.PlayableInRegister;

public abstract class SpecialCard extends Card implements PlayableInRegister {
    public SpecialCard(CardName cardName){
        super(cardName);
    }

}
