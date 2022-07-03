package client_package.clientSideMessages;

import client_package.AI.AIClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.gamelogic.Player;
import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardFactory;
import server_package.gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mark Ringer, Vivian Kafadar
 */

public class MessageCurrentCards extends Message {
    ArrayList<Card> cards;
    ArrayList<Player> players;

    /**
     * @param cards
     * @param players
     */
    public MessageCurrentCards(ArrayList<Card> cards, ArrayList<Player> players){

        this.cards = cards;
        this.players = players;
        type = "CurrentCards";
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cards.size(); i++) {
            JsonObject infoJObject = new JsonObject();
            infoJObject.add("clientID", new JsonPrimitive(players.get(i).getClient().getId()));
            infoJObject.add("card", new JsonPrimitive(cards.get(i).toString()));
            jsonArray.add(infoJObject);
        }
        jsonObject.add("activeCards",jsonArray);
        content = jsonObject;

        System.out.println("Created Register Message: " + this);
    }

    /**
     * @param jsonObject
     * @throws IOException
     */
    public MessageCurrentCards(JsonObject jsonObject) throws IOException {
        JsonArray jsonArray = jsonObject.get("activeCards").getAsJsonArray();
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
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

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
