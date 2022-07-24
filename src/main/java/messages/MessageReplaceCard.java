package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;

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
        type = "ReplaceCard";
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
        card = content.get("newCard").getAsString();
        register = content.get("register").getAsInt();
        //Server.serverLogger.info("Created Replace Card Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {


    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        ArrayList<Card> registers = client.getPlayer().getRegisterCardsOwn();
        Card card = new CardFactory().createCard(client_package.client_gamelogic.cards.CardName.parseCardName(this.card));

        registers.remove(register);
        registers.add(register,card);
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));


    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
