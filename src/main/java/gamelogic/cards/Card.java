package gamelogic.cards;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gamelogic.JsonSerializable;
import gamelogic.Player;

import java.util.Objects;

public abstract class Card implements JsonSerializable {
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

    public abstract void discard();


    public abstract void activateCard();

    public JsonElement toJson(){
        return new JsonPrimitive(cardName.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getCardName() == card.getCardName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardName());
    }

    @Override
    public String toString() {
        return cardName.toString();
    }
}
