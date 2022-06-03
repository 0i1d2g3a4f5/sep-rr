package gamelogic.Card;


public abstract class Card {
    public CardName cardName;

    public Card(CardName cardName){
        this.cardName = cardName;
    }

    public CardName getCardName(){
        return cardName;
    }


    public abstract void PlayCard();
}
