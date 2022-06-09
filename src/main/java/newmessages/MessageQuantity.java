package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageQuantity extends Message{

    public int clientID;
    public int cardsInHand;

    public MessageQuantity(int clientID, int cardsInHand) {
        super(clientID, cardsInHand);
        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
        type = "NotYourCards";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("cardsInHand", new JsonPrimitive(cardsInHand));
        content = jsonObject;
        System.out.println("Created Quantity Message: " + this);
    }

    public MessageQuantity(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        cardsInHand = content.get("cardsInHand").getAsInt();
        System.out.println("Created Quantity Message: " + this + " from JSON: " + jsonObject);
    }
}
