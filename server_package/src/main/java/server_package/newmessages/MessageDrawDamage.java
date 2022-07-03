package server_package.newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.gamelogic.cards.Card;
import server_package.gamelogic.cards.CardFactory;
import server_package.gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Vivian Kafadar
 */

public class MessageDrawDamage extends Message {

    public int clientID;
    private ArrayList<Card> damageCards;
    private JsonObject jsonObject;

    /**
     * @param clientID
     * @param damageCards
     */
    public MessageDrawDamage(int clientID, ArrayList<CardName> damageCards) {
        //TODO check if correct
        this.clientID = clientID;
        type = "DrawDamage";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < damageCards.size(); i++) {
            JsonObject infoJObject = new JsonObject();
            infoJObject.add("damageCards", new JsonPrimitive(damageCards.get(i).toString()));
            jsonArray.add(infoJObject);
        }
        jsonObject.add("damageCards",jsonArray);
        content = jsonObject;
        System.out.println("Created DrawDamage Message: " + this);
    }

    /**
     * @param jsonObject
     * @throws IOException
     */
    public void MessageDrawDamage(JsonObject jsonObject) throws IOException {
        //TODO check if correct - please fix
        JsonArray jsonArray = jsonObject.get("damageCards").getAsJsonArray();
        ArrayList<Card> damageCards = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        for (int i = 0; i < damageCards.size(); i++) {
            damageCards.add(cardFactory.createCard(CardName.valueOf(jsonArray.get(i).getAsString())));
        }
        this.damageCards = damageCards;

        System.out.println("Created Register Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
