package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessagePlayCard extends Message{

    public String card;

    /**
     * converts message to json
     *
     * @param card
     * @author Isabel Muhm
     */
    public MessagePlayCard(String card){
        this.card = card;
        type = "PlayCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        //Server.serverLogger.info("Created Play Card Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessagePlayCard(JsonObject jsonObject){
        super(jsonObject);
        card = content.get("card").getAsString();
        //Server.serverLogger.info("Created Play Card Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
