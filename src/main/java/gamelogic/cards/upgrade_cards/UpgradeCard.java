package gamelogic.cards.upgrade_cards;

import gamelogic.Game;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;

public abstract class UpgradeCard extends Card {
    public UpgradeCard(CardName cardName) {
        super(Game.getInstance(), cardName);
    }
}
