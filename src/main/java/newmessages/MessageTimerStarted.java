package newmessages;

import com.google.gson.JsonObject;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageTimerStarted extends Message{

    //public ArrayList<int> players;

    public MessageTimerStarted(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
       // Server.serverLogger.info("Created Timer Started Message: " + this);
    }

    /**
     *
     * @param jsonObject
     */
    public MessageTimerStarted(JsonObject jsonObject){
        super(jsonObject);
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
