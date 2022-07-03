package client_package.client_gamelogic.cards.special_cards;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.PlayableInRegister;

public abstract class SpecialCard extends Card {
    public SpecialCard(CardName cardName){
        super(cardName);
    }

}
