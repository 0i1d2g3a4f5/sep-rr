package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;

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
        //Server.serverLogger.info("Created Your Cards Message: " + this);
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

        //Server.serverLogger.info("Created Your Cards Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param aClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient aClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

        ArrayList<Card> handCards = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();

        for (CardName card:cardsInHand) {
            handCards.add(cardFactory.createCard(card));
        }

        client.getPlayer().setHandCards(handCards);
        //TODO (Vivian): Mark, should we keep this?
        System.out.println("HANDCARDS: ");
        for(Card card : handCards){
            System.out.println(card.getCardName().toString());
        }

        Thread thread = new Thread(){
            @Override
            public void run() {
                client.getClientApplication().activateCardSelection(true);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
            }
        };
        thread.setDaemon(true);
        thread.start();
        //System.out.println("Server: Program pls3 ");

        /*Scanner scanner = new Scanner(System.in);
        if (utility.SearchMethods.emptyArraySpaces(client.getPlayer().getRegisterCards())>0){
            System.out.println("Your Hand Cards: "+ client.getPlayer().getHandCards());
            System.out.println("Your Register: "+ Arrays.toString(client.getPlayer().getRegisterCards()));
            System.out.println("please insert the position of the card you want to pick");
            int posHand = scanner.nextInt();
            System.out.println("please insert the position the repository");
            int posRepository = scanner.nextInt();
            System.out.println("scanned");
            client.getPlayer().selectCard(posHand,posRepository);

        }*/





    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }

}
