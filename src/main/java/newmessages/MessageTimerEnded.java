package newmessages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gamelogic.Player;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageTimerEnded extends Message{

    /**
     * @param latePlayers
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
        //System.out.println("Created StartTime Message: " + this);
        //Server.serverLogger.info("Created Timer Ended Message: " + this);

    }

    /**
     * @param jsonObject
     */
    public MessageTimerEnded(JsonObject jsonObject){
        super(jsonObject);
        //Server.serverLogger.info("Created Timer Ended Message: " + this + " from JSON: " + jsonObject);
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

    }
}
