package gamelogic.Card.special_cards;

import gamelogic.Card.Card;

import static gamelogic.Card.CardName.*;

public class RepeatRoutine extends SpecialCard {
    public RepeatRoutine(){
        super(REPEAT_ROUTINE);
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
