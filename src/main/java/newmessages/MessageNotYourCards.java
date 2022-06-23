package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageNotYourCards extends Message{

    public int clientID;
    public int cardsInHand;

    public MessageNotYourCards(int clientID, int cardsInHand) {
        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
        type = "NotYourCards";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("cardsInHand", new JsonPrimitive(cardsInHand));
        content = jsonObject;
        System.out.println("Created Quantity Message: " + this);
    }

    public MessageNotYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        cardsInHand = content.get("cardsInHand").getAsInt();
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
