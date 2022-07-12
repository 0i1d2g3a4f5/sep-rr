package newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Player;
import gamelogic.cards.Card;
import gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import server_package.SClient;
import server_package.Server;

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
        JsonObject messageContent = new JsonObject();
        messageContent.add("clientID", new JsonPrimitive(clientID));
        JsonArray jsonArray = new JsonArray();
        for(int i = 0; i<damageCards.size(); i++){
            jsonArray.add(damageCards.get(i).toString());
        }
        messageContent.add("cards", jsonArray);
        content = messageContent;
    }

    public MessageDrawDamage(JsonObject jsonObject) {
        super(jsonObject);
        CardFactory cardFactory = new CardFactory();
        JsonArray jsonArray = content.get("cards").getAsJsonArray();
        ArrayList<Card> damageCards1 = new ArrayList<>();
        for(int i=0; i<jsonArray.size(); i++){
            try {
                damageCards1.add(cardFactory.createCard(CardName.parseCardName(jsonArray.get(i).getAsString())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        damageCards=damageCards1;
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
        Server.serverLogger.info("Created DrawDamage Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @author Mark Ringer
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        client.getPlayer().drawDamage();
    }
}
