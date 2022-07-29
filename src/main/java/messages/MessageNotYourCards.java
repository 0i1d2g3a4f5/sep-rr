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
     * converts message to json
     *
     * @param clientID
     * @param cardsInHand
     * @author Isabel Muhm
     */
    public MessageNotYourCards(int clientID, int cardsInHand) {
        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
        type = "NotYourCards";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("cardsInHand", new JsonPrimitive(cardsInHand));
        content = jsonObject;
    }

    /**
     * converts string to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageNotYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        cardsInHand = content.get("cardsInHand").getAsInt();
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
     * informs about number of available cards of other client
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Sarp Cagin Erdogan
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

    /**
     * informs AI about number of available cards of other clients
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Sarp Cagin Erdogan
     */
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
