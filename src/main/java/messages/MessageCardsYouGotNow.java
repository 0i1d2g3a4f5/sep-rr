package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.cards.Card;
import gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Mark Ringer
 */

public class MessageCardsYouGotNow extends Message{
    ArrayList<CardName> cards = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    /**
     * @param cards
     */
    public MessageCardsYouGotNow(ArrayList<Card> cards){

        for (Card card:cards
             ) {
            this.cards.add(card.getCardName());
            this.names.add(card.getCardName().toString());
        }

        type = "CardsYouGotNow";
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cards.size(); i++) {
            jsonArray.add(cards.get(i).toString());

        }
        jsonObject.add("cards",jsonArray);

        content = jsonObject;
        //Server.serverLogger.info("Created Cards You Got Know Message: " + this);
    }

    /**
     * @param jsonObject
     * @throws IOException
     */
    public MessageCardsYouGotNow(JsonObject jsonObject) {
        super(jsonObject);
        JsonArray jsonArray = jsonObject.get("cards").getAsJsonArray();
        ArrayList<CardName> cards = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        for (int i = 0; i < jsonArray.size(); i++) {
            names.add(jsonArray.get(i).getAsString());
            cards.add(CardName.valueOf(jsonArray.get(i).getAsString()));
        }
        this.names = names;
        this.cards = cards;

        //Server.serverLogger.info("Created Cards You Got Know Message: " + this + " from JSON: " + jsonObject);
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
        Client.clientLogger.debug("CardsYouGotNow activated");
        Client.clientLogger.info("CardsYouGotNow: "+ cards);
        client.getPlayer().autofill(names);


        Client.clientLogger.debug("Your Register: "+ client.getPlayer().getRegisterCardsOwn());

        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));
    }


    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
