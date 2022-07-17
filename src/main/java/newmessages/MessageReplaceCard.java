package newmessages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

public class MessageReplaceCard extends Message {


    public String card;
    public int register;

    /**
     * @param card
     * @param register
     */
    public MessageReplaceCard(int register, CardName card, int clientID) {
        this.card = String.valueOf(card);
        this.register = register;
        type = "SelectedCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("register", new JsonPrimitive(register));

        jsonObject.add("newCard", new JsonPrimitive(String.valueOf(card)));
        jsonObject.add("clientID",new JsonPrimitive(clientID));
        content = jsonObject;
        //Server.serverLogger.info("Created Replace Card Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageReplaceCard(JsonObject jsonObject) {
        super(jsonObject);
        card = content.get("card").getAsString();
        register = content.get("register").getAsInt();
        //Server.serverLogger.info("Created Replace Card Message: " + this + " from JSON: " + jsonObject);
    }

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
