package gamelogic.Card.DamageCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Game;
import gamelogic.Player;

public class TrojanHorse extends Card {

    public TrojanHorse(){
        super(CardName.TROJAN_HORSE);
    }

    /**
     * if you discard a TrojanHorse, you will immediately get two Spam cards.
     */
    public void PlayCard(){
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        player.getDiscardPile().add(Game.getInstance().getSpamDrawPile().pop());
        //remove card from register and replace it with a new one.
    }
}
