package client_package.client_gamelogic.cards;


import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;


import java.util.Objects;

public abstract class Card{
    protected CardName cardName;

    protected CPlayer player;
    protected Game game;

    public void setPlayer(CPlayer player){
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
