package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

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
        //Server.serverLogger.info("Created Not Your Cards Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageNotYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        cardsInHand = content.get("cardsInHand").getAsInt();
        //Server.serverLogger.info("Created Not Your Cards Message: " + this + " from JSON: " + jsonObject);
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
        for(int i=0; i<client.getPlayerList().size(); i++){
            if(clientID==client.getPlayerList().get(i).getId()){
                client.getPlayerList().get(i).getPlayer().setAvailableCardsOther(cardsInHand);
                break;
            }
        }
    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        for(int i=0; i<sentientClient.getPlayerList().size(); i++){
            if(clientID==sentientClient.getPlayerList().get(i).getId()){
                sentientClient.getPlayerList().get(i).getPlayer().setAvailableCardsOther(cardsInHand);
                break;
            }
        }
    }
}
