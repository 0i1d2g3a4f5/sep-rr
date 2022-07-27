package messages;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageCardPlayed extends Message{

    public int clientID;
    public String card;

    /**
     * converts message to json
     *
     * @param clientID
     * @param card
     * @author Isabel Muhm
     */
    public MessageCardPlayed(int clientID, String card){
        this.clientID = clientID;
        this.card = card;
        type = "CardPlayed";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        //Server.serverLogger.info("Created CardPlayed Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageCardPlayed(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        card = content.get("card").getAsString();
        //Server.serverLogger.info("Created CardPlayed Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @author Ringer
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        /*
        TBD
        if (sClient != null) {
            sClient.getPlayer().placeCard(CardName.parseCardName(card), 1);
            // TODO check if okay
        } else {
            throw new ClientNotFoundException("SClient with ID = " + clientID + " not found");
        }

         */
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
