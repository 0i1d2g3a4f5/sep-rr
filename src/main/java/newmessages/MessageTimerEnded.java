package newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Player;
import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Isabel Muhm
 */

public class MessageTimerEnded extends Message{

    public MessageTimerEnded(ArrayList<Player> latePlayers){
        super();
        type = "TimerEnded";
        JsonObject jsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        for (Player player:latePlayers
             ) {
            jsonArray.add(player.getClient().getId());
        }
        jsonObject.add("clientIDs",jsonArray);
        content = jsonObject;
        System.out.println("Created StartTime Message: " + this);

    }

    public MessageTimerEnded(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created StartTime Message: " + this + " from JSON: " + jsonObject);
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

    }
}
