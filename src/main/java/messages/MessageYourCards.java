package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.cards.CardName;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Mark Ringer, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageYourCards extends Message{
    public CardName cardsInHand[];

    /**
     * converts message to json
     *
     * @param cardsInHand
     * @author Isabel Muhm
     */
    public MessageYourCards(CardName[] cardsInHand) {
        this.cardsInHand = cardsInHand;
        type = "YourCards";
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cardsInHand.length; i++) {
            jsonArray.add(cardsInHand[i].toString());
        }

        jsonObject.add("cardsInHand",jsonArray);
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel
     */
    public MessageYourCards(JsonObject jsonObject){
        super(jsonObject);
        JsonArray cardsJArray= content.get("cardsInHand").getAsJsonArray();
        cardsInHand = new CardName[9];
        for (int i = 0; i < cardsJArray.size(); i++) {
            cardsInHand[i] =CardName.parseCardName(cardsJArray.get(i).getAsString());
        }
    }

    /**
     * @param aClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient aClient) throws IOException, ClientNotFoundException {

    }

    /**
     * informs client about their new available programming cards
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
            for(int i=0; i<cardsInHand.length; i++){
                client.getPlayer().getAvailableCardsOwn().set(i, new CardFactory().createCard(client_package.client_gamelogic.cards.CardName.parseCardName(cardsInHand[i].toString())));
            }
            client.getClientApplication().activateAvailableProgrammingSelection(true);
            client.getClientApplication().activateRegisterSelection(true);
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEOTHERSREGISTERS, new TaskContent()));
       }

    /**
     * informs AI about their new available programming cards
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        for(int i=0; i<cardsInHand.length; i++){
            sentientClient.getPlayer().getAvailableCardsOwn().set(i, new CardFactory().createCard(client_package.client_gamelogic.cards.CardName.parseCardName(cardsInHand[i].toString())));
        }
        sentientClient.getSentientBehaviour().setCanChooseNext(true);
        sentientClient.getSentientBehaviour().chooseAllCards();
    }

}
