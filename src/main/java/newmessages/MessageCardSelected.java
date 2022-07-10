package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.AI.AIClient;
import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.ThisCPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageCardSelected extends Message{

    public int clientID;
    public int register;
    public boolean filled;

    /**
     * @param clientID
     * @param register
     * @param filled
     */
    public MessageCardSelected(int clientID, int register, boolean filled){
        this.clientID = clientID;
        this.register = register;
        this.filled = filled;
        type = "CardSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("register", new JsonPrimitive(register));
        jsonObject.add("filled", new JsonPrimitive(filled));
        content = jsonObject;
        //System.out.println("Created Register Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageCardSelected(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        register = content.get("register").getAsInt();
        filled = content.get("filled").getAsBoolean();
        //System.out.println("Created Register Message: " + this + " from JSON: " + jsonObject);
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
        if(clientID ==client.getId()){
            ThisCPlayer player = client.getPlayer();
            player.getHandCards().remove(player.getSelectedCard());
            player.placeRegisterCards(player.getSelectedCard(),register);

            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));

        }
        else {
            for(CPlayer player:client.getGame().getPlayerList()){
                if(player.getClientID()==clientID){
                    player.addHandCards(register);
                }
            }
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEOTHERSREGISTERS, new TaskContent()));


        }
        Scanner scanner = new Scanner(System.in);
        if (utility.SearchMethods.emptyArraySpaces(client.getPlayer().getRegisterCards())>0){
            System.out.println("Your Hand Cards: "+ client.getPlayer().getHandCards());
            System.out.println("Your Register: "+ Arrays.toString(client.getPlayer().getRegisterCards()));
            System.out.println("please insert the position of the card you want to pick");
            int posHand = scanner.nextInt();
            System.out.println("please insert the position the repository");
            int posRepository = scanner.nextInt();
            client.getPlayer().selectCard(posHand,posRepository);

        }
    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
