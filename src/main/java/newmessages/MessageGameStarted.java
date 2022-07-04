package newmessages;

import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.Player;
import client_package.client_gamelogic.ThisPlayer;
import client_package.client_gamelogic.map.GameBoard;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Vivian Kafadar
 */
public class MessageGameStarted extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageGameStarted(int clientID){
        this.clientID = clientID;
        type = "GameStarted";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Started Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageGameStarted(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Started Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

        for (client_package.Client client2:client.getPlayerList()
             ) {
            if(client2.getId() !=client.getId()){
                client_package.client_gamelogic.Game.getInstance().getPlayerList().add(new Player(client2.getId(),client2.getRoboColor()));
            } else {
                client.setPlayer(new ThisPlayer(client2.getId(),client2.getRoboColor()));
                client_package.client_gamelogic.Game.getInstance().getPlayerList().add(client.getPlayer());
            }

        }
        //client_package.client_gamelogic.Game.getInstance().setMap(new GameBoard());

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException{

    }
}