package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import server_package.Client;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm
 */

public class MessagePlayedCard extends Message{

    public int clientID;
    public String card;

    public MessagePlayedCard(int clientID, String card){
        super(clientID, card);
        this.clientID = clientID;
        this.card = card;
        type = "CardPlayed";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        System.out.println("Created CardPlayed Message: " + this);
    }

    public MessagePlayedCard(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        card = content.get("card").getAsString();
        System.out.println("Created CardPlayed Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @author Ringer
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {
        if (client != null) client.getPlayer().playCard(CardName.parseCardName(card));
        else throw new ClientNotFoundException("Client with ID = "+ clientID +" not found");
    }
}
