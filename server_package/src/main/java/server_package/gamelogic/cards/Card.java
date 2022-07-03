package server_package.gamelogic.cards;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import server_package.gamelogic.Game;
import server_package.gamelogic.JsonSerializable;
import server_package.gamelogic.Player;

import java.util.Objects;

public abstract class Card implements JsonSerializable {
    protected CardName cardName;

    protected Player player;
    protected Game game;

    public void setPlayer(Player player){
        this.player =player;
    }
    public void setGame(Game game){this.game = game;}

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
