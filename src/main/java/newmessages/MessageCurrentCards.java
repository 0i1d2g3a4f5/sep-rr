package newmessages;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Player;
import gamelogic.cards.Card;
import gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import server_package.SClient;

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
        JsonObject messageContent = new JsonObject();
        JsonArray pairList = new JsonArray();
        for(int i=0; i<Math.min(cards.size(), players.size()); i++){
            JsonObject temp = new JsonObject();
            temp.add("clientID", new JsonPrimitive(players.get(i).getClient().getId()));
            temp.add("card", new JsonPrimitive(cards.get(i).getCardName().toString()));
            pairList.add(temp);
        }
        messageContent.add("activeCards", pairList);
        content = messageContent;
    }

    /**
     * @param jsonObject
     * @throws IOException
     */
    public MessageCurrentCards(JsonObject jsonObject) {
        super(jsonObject);
        ArrayList<Card> cards1 = new ArrayList<>();
        JsonArray jsonArray = content.get("activeCards").getAsJsonArray();
        CardFactory cardFactory = new CardFactory();
        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                cards1.add(cardFactory.createCard(CardName.parseCardName(jsonArray.get(i).getAsJsonObject().get("card").getAsString())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.cards = cards;
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
