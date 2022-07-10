package gamelogic.cards.damage_card;

import gamelogic.Game;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import gamelogic.cards.PlayableInRegister;

public abstract class DamageCard extends Card implements PlayableInRegister {
    public DamageCard(CardName cardName) {
        super(Game.getInstance(), cardName);
    }

    @Override
    public void discard() {

    }

    protected void removeFromRegister(){
        player.clearThisRegister(game.getActiveRegister());
    }

}
