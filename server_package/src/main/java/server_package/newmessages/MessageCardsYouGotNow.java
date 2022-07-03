package server_package.newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardFactory;
import server_package.gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Mark Ringer
 */

public class MessageCardsYouGotNow extends Message{
    ArrayList<Card> cards;

    /**
     * @param cards
     */
    public MessageCardsYouGotNow(ArrayList<Card> cards){

        this.cards = cards;
        type = "CardsYouGotNow";
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cards.size(); i++) {
            jsonArray.add(cards.toString());

        }
        jsonObject.add("cards",jsonArray);

        content = jsonObject;
        System.out.println("Created Register Message: " + this);
    }

    /**
     * @param jsonObject
     * @throws IOException
     */
    public MessageCardsYouGotNow(JsonObject jsonObject) throws IOException {
        JsonArray jsonArray = jsonObject.get("cards").getAsJsonArray();
        ArrayList<Card> cards = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        for (int i = 0; i < cards.size(); i++) {
            cards.add(cardFactory.createCard(CardName.valueOf(jsonArray.get(i).getAsString())));
        }
        this.cards = cards;

        System.out.println("Created Register Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}