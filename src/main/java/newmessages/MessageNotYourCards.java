package newmessages;

import client_package.client_gamelogic.CPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageNotYourCards extends Message{

    public int clientID;
    public int cardsInHand;

    /**
     * @param clientID
     * @param cardsInHand
     */
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

    /**
     * @param jsonObject
     */
    public MessageNotYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        cardsInHand = content.get("cardsInHand").getAsInt();
        System.out.println("Created Quantity Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override

    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        System.out.println("NotYourCards "+ cardsInHand);
        /*

        ArrayList<CPlayer> playerList = client.getGame().getPlayerList();

        for (CPlayer player:playerList) {
            if(player.getClientID()==clientID){
                player.setHandCards(cardsInHand);
                break;
            }
        }

         */

    }
}
