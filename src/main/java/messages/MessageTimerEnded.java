package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Player;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageTimerEnded extends Message{

    /**
     * converts json to message
     *
     * @param latePlayers
     * @auhtor Isabel Muhm
     */
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
    }

    /**
     * @param jsonObject
     */
    public MessageTimerEnded(JsonObject jsonObject){
        super(jsonObject);
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
     * informs client gamecontroller about end of timer
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException, InterruptedException {
        client.getClientApplication().getGameController().endTimer();
    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
