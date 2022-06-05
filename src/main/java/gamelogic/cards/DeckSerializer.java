package gamelogic.cards;

import com.google.gson.Gson;
import gamelogic.cards.damage_card.Spam;
import gamelogic.cards.damage_card.TrojanHorse;
import gamelogic.cards.damage_card.Virus;
import gamelogic.cards.damage_card.Worm;
import gamelogic.cards.special_cards.EnergyRoutine;
import gamelogic.cards.special_cards.RepeatRoutine;
import gamelogic.cards.special_cards.SpamFolder;
import gamelogic.cards.special_cards.SpeedRoutine;

import java.io.IOException;
import java.util.Stack;

public class DeckSerializer {

    /**
     * @auther Ringer
     * @param serializedCardJason
     * @param cardName
     * @return Card
     * @throws IOException
     */
    public Card deserializeCard(String serializedCardJason, CardName cardName) throws IOException {
        Class className;
        Gson gson = new Gson();
        switch (cardName){
            case SPAM -> {
                className = Spam.class;
            }
            case TROJAN_HORSE -> {
                className = TrojanHorse.class;
            }
            case VIRUS -> {
                className = Virus.class;
            }
            case WORM -> {
                className = Worm.class;
            }
            case SPEED_ROUTINE -> {
                className = SpeedRoutine.class;
            }
            case SPAM_FOLDER -> {
                className = SpamFolder.class;
            }
            case REPEAT_ROUTINE -> {
                className = RepeatRoutine.class;
            }
            case ENERGY_ROUTINE -> {
                className = EnergyRoutine.class;
            }
            default -> {
                throw new IOException("Card does not exist");
            }
        }
        return (Card) gson.fromJson(serializedCardJason,className);
    }

    public Stack<Card> deserializeDeck(){

        return null;
    }

    public void serializeCard(){

    }

}
