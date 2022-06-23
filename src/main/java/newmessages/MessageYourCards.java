package newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;

public class MessageYourCards extends Message{



    public int clientID;
    public CardName cardsInHand[];

    public MessageYourCards(int clientID, CardName[] cardsInHand) {

        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
        type = "YourCards";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cardsInHand.length; i++) {
            jsonArray.add(cardsInHand[i].toString());
        }

        jsonObject.add("cardsInHand",jsonArray);
        content = jsonObject;
        System.out.println("Created Quantity Message: " + this);
    }

    public MessageYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        JsonArray cardsJArray= content.get("cardsInHand").getAsJsonArray();
        for (int i = 0; i < cardsJArray.size(); i++) {
            cardsInHand[i] =CardName.valueOf(cardsJArray.get(i).getAsString());
        }

        System.out.println("Created Quantity Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
