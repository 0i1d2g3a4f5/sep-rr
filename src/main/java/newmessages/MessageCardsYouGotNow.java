package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.AI.AIClient;
import client_package.Client;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.cards.Card;
import gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Mark Ringer
 */

public class MessageCardsYouGotNow extends Message{
    ArrayList<CardName> cards = new ArrayList<>();

    /**
     * @param cards
     */
    public MessageCardsYouGotNow(ArrayList<Card> cards){

        for (Card card:cards
             ) {
            this.cards.add(card.getCardName());
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
            cards.add(CardName.valueOf(jsonArray.get(i).getAsString()));
        }
        this.cards = cards;

        //Server.serverLogger.info("Created Cards You Got Know Message: " + this + " from JSON: " + jsonObject);
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
        Client.clientLogger.debug("CardsYouGotNow activated");
        client_package.client_gamelogic.cards.CardFactory cardFactory = new client_package.client_gamelogic.cards.CardFactory();
        Client.clientLogger.info("CardsYouGotNow: "+ cards);
        for (int i = 0; i < client.getPlayer().getRegisterCards().length; i++) {
            if(client.getPlayer().getRegisterCards()[i] == null){
                client.getPlayer().placeRegisterCards(cardFactory.createCard(cards.get(0)),i);
                cards.remove(0);
            }
        }
        Client.clientLogger.debug("Your Register: "+ client.getPlayer().getRegisterCards());

        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));
    }


    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
