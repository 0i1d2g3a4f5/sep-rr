package client_package.client_gamelogic.cards;

import client_package.client_gamelogic.cards.damage_card.Spam;
import client_package.client_gamelogic.cards.damage_card.TrojanHorse;
import client_package.client_gamelogic.cards.damage_card.Virus;
import client_package.client_gamelogic.cards.damage_card.Worm;
import client_package.client_gamelogic.cards.special_cards.EnergyRoutine;
import client_package.client_gamelogic.cards.special_cards.RepeatRoutine;
import client_package.client_gamelogic.cards.special_cards.SpamFolder;
import client_package.client_gamelogic.cards.special_cards.SpeedRoutine;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import utility.JsonReader;

import java.io.IOException;
import java.util.Stack;

/**
 * @author Mark Ringer
 */
public class DeckSerializer {

    /**
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

    /**
     * @param element
     * @return
     * @throws IOException
     */
    public Stack<Card> deserializeDeck(JsonElement element) throws IOException {
        JsonArray array = element.getAsJsonArray();
        Stack<Card> deck= new Stack<>();
        for (JsonElement jsonType:array) {
            deck.add(new CardFactory().createCard(CardName.parseCardName(jsonType.toString())));
        }
        return deck;
    }

    /**
     * @param mapName
     * @return
     * @throws IOException
     */
    public Stack<Card> builtDeck(String mapName) throws IOException {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(readFile(mapName),JsonElement.class);
        return deserializeDeck(element);
    }

    /**
     * @param mapName
     * @return
     */
    private String readFile(String mapName){
        String filePath = "src/main/resources/DeckModels/"+mapName+".json";
        return new JsonReader().readFile(filePath);
    }

    /**
     * @param deck
     * @return
     */
    public JsonElement serializeDeck(Stack<Card> deck){
        JsonArray jsonArray = new JsonArray();
        for (Card card:deck) {
            jsonArray.add(card.toJson());
        }
        return jsonArray;
    }
}
