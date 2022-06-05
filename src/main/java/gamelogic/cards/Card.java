package gamelogic.cards;


import gamelogic.Player;

public abstract class Card {
    protected CardName cardName;

    protected Player player;

    public void setPlayer(Player player){
        this.player =player;
    }

    public Card(CardName cardName){
        this.cardName = cardName;

    }

    public CardName getCardName(){
        return cardName;
    }


    public abstract void PlayCard();
}
