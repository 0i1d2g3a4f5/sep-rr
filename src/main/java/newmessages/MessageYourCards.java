package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mark Ringer, Vivian Kafadar
 */

public class MessageYourCards extends Message{

    public int clientID;
    public CardName cardsInHand[];

    /**
     * @param clientID
     * @param cardsInHand
     */
    public MessageYourCards(int clientID, CardName[] cardsInHand) {

        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
        type = "YourCards";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cardsInHand.length; i++) {
            jsonArray.add(cardsInHand[i].toString());
        }

        jsonObject.add("cardsInHand",jsonArray);
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageYourCards(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        JsonArray cardsJArray= content.get("cardsInHand").getAsJsonArray();
        cardsInHand = new CardName[9];
        for (int i = 0; i < cardsJArray.size(); i++) {
            cardsInHand[i] =CardName.parseCardName(cardsJArray.get(i).getAsString());
        }
    }

    /**
     * @param aClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient aClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        System.out.println("Cards You Got: "+ cardsInHand);


        ArrayList<Card> handCards = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();

        for (CardName card:cardsInHand) {
            handCards.add(cardFactory.createCard(card));
        }

        client.getPlayer().setHandCards(handCards);

        System.out.println(client.getPlayer().handCards);

        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));

        /*Scanner scanner = new Scanner(System.in);
        if (utility.SearchMethods.emptyArraySpaces(client.getPlayer().getRegisterCards())>0){
            System.out.println("Your Hand Cards: "+ client.getPlayer().getHandCards());
            System.out.println("Your Register: "+ Arrays.toString(client.getPlayer().getRegisterCards()));
            System.out.println("please insert the position of the card you want to pick");
            int posHand = scanner.nextInt();
            System.out.println("please insert the position the repository");
            int posRepository = scanner.nextInt();
            client.getPlayer().selectCard(posHand,posRepository);

        }*/
        client.getClientApplication().activateCardSelection();





    }

}
