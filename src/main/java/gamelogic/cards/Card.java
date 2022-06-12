package gamelogic.cards;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.JsonSerializable;
import gamelogic.Player;
import gamelogic.cards.damage_card.Spam;
import gamelogic.cards.damage_card.Virus;
import gamelogic.cards.programming_cards.LeftTurn;
import gamelogic.cards.upgrade_cards.permanent.CorruptionWave;

import java.io.IOException;
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


    public abstract void PlayCard();

    public JsonElement toJson(){
        return new JsonPrimitive(cardName.toString());
    }

    public static Card parseCard(CardName cardName) throws IOException {
        switch (cardName){
            case SPAM -> {
                return new Spam();
            }
            case TROJAN_HORSE -> {
            }
            case VIRUS -> {
                return new Virus();
            }
            case WORM -> {
            }
            case AGAIN -> {
            }
            case BACK_UP -> {
            }
            case LEFT_TURN -> {
                return new LeftTurn();
            }
            case MOVE_ONE -> {
            }
            case MOVE_TWO -> {
            }
            case MOVE_THREE -> {
            }
            case POWER_UP -> {
            }
            case RIGHT_TURN -> {
            }
            case U_TURN -> {
            }
            case ADMIN_PRIVILEGE -> {
            }
            case CORRUPTION_WAVE -> {
                return new CorruptionWave();
            }
            case BLUE_SCREEN -> {
            }
            case CRAB_LEGS -> {
            }
            case BRAKES -> {
            }
            case DEFLECTOR_SHIELD -> {
            }
            case CACHE_MEMORY -> {
            }
            case DEFRAG_GIZMO -> {
            }
            case DOUBLE_BARREL_LASER -> {
            }
            case MODULAR_CHASSIS -> {
            }
            case FIREWALL -> {
            }
            case PRESSOR_BEAM -> {
            }
            case HOVER_UNIT -> {
            }
            case RAIL_GUN -> {
            }
            case MEMORY_STICK -> {
            }
            case RAMMING_GEAR -> {
            }
            case MINI_HOWITZER -> {
            }
            case REAR_LASER -> {
            }
            case SCRAMBLER -> {
            }
            case TRACTOR_BEAM -> {
            }
            case SIDE_ARMS -> {
            }
            case TROJAN_NEEDLER -> {
            }
            case TELEPORTER -> {
            }
            case VIRUS_MODULE -> {
            }
            case BOINK -> {
            }
            case HACK -> {
            }
            case ENERGY_ROUTINE -> {
            }
            case MANUAL_SORT -> {
            }
            case MEMORY_SWAP -> {
            }
            case REBOOST -> {
            }
            case RECHARGE -> {
            }
            case RECOMPILE -> {
            }
            case SPAM_FOLDER_ROUTINE -> {
            }
            case REFRESH -> {
            }
            case ZOOP -> {
            }
            case SPEED_ROUTINE -> {
            }
            case SPAM_FOLDER -> {
            }
            case REPEAT_ROUTINE -> {
            }
            case SANDBOX_ROUTINE -> {
            }
            case WEASEL_ROUTINE -> {
            }
            case SPAM_BLOCKER -> {
            }
            default -> throw new IOException("Card "+cardName+" not found");
        }
        return null;
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
}
