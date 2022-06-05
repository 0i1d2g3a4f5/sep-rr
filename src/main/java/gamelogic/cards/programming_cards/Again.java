package gamelogic.cards.programming_cards;

import static gamelogic.cards.CardName.AGAIN;

public class Again extends ProgrammingCard {

    public Again() {super(AGAIN);}

    @Override
    public void PlayCard() {
        /*
        Repeat the programming in your previous register.
        If your previous register was a damage card, draw a card from the top of your deck, and play that card this register.
        If you used an upgrade in your previous register that allowed you to play multiple programming cards, re-execute the second card.
        This card cannot be played in the first register.
         */
    }
}
