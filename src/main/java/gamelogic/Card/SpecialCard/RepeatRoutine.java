package gamelogic.Card.SpecialCard;

import gamelogic.Card.Card;
import gamelogic.Card.CardName;
import gamelogic.Player;

public class RepeatRoutine extends Card {
    public RepeatRoutine(){
        super(CardName.REPEAT_ROUTINE);
    }

    /**
     * @author Qinyi
     * repeat the programming in the previous register.
     * If the previous discarded card was a damage card, draw a card from the top of the deck and play it.
     */
    public void PlayCard(){
        player.getLastPlayedCard().PlayCard();
    }
}
