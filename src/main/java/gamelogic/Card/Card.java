package gamelogic.Card;
import static gamelogic.Card.CardName.*;


public abstract class Card {
    public CardName cardName;

    public Card(CardName cardName){
        this.cardName = cardName;
    }

    public CardName getCardName(){
        return cardName;
    }
}
