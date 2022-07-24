package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

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
        jsonObject.add("register", new JsonPrimitive(register+1));
        jsonObject.add("filled", new JsonPrimitive(filled));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageCardSelected(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        register = content.get("register").getAsInt()-1;
        filled = content.get("filled").getAsBoolean();
        //Server.serverLogger.info("Created Register Message: " + this + " from JSON: " + jsonObject);
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
        if(clientID==client.getId()){
            if(filled){
                client.getPlayer().placeSelectedToRegisterOwn();
            }
            else{
                client.getPlayer().removeCardFromRegisterOwn();
            }
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_HANDCARDS, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATE_PROGCARDS, new TaskContent()));
        }
        else{
            System.out.println("OTHER");
            if(filled){
                System.out.println("OTHER FILLED");
                client.playerFromId(clientID).getPlayer().getRegisterCardsOther().set(register, true);
            }
            else{
                System.out.println("OTHER EMPTY");
                client.playerFromId(clientID).getPlayer().getRegisterCardsOther().set(register, false);
            }
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEOTHERSREGISTERS, new TaskContent()));
        }


    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            if(filled){
                sentientClient.getPlayer().placeSelectedToRegisterOwn();
            }
            else{
                sentientClient.getPlayer().removeCardFromRegisterOwn();
            }
        }
        else{
            sentientClient.playerFromId(clientID).getPlayer().updateCardOfRegisterOther(register, filled);
        }
    }
}