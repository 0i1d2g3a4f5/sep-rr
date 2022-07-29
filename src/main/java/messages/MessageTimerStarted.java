package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageTimerStarted extends Message{

    public MessageTimerStarted(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageTimerStarted(JsonObject jsonObject){
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
     * informs game controller of client about start of 30 sec timer
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Isabel Muhm
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException, InterruptedException {
        client.getClientApplication().getGameController().startTimer();
    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
