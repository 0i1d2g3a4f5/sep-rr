package messages;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageActivePhase extends Message{

    public int phase;

    /**
     * @param phase
     * @author Isabel Muhm
     */
    public MessageActivePhase(int phase){
        this.phase = phase;
        type = "ActivePhase";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("phase", new JsonPrimitive(phase));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageActivePhase(JsonObject jsonObject) {
        super(jsonObject);
        phase = content.get("phase").getAsInt();
    }

    /**
     * @param sClient
     */
    @Override
    public void activateMessageInBackend(SClient sClient) {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        Client.clientLogger.info("Active Phase: Phase " + phase);
        client.getGame().setPhase(phase);
        client.getClientApplication().getGameController().stopChoosingDirection();

        if(phase == 2 || phase == 3){
            client.getPlayer().phaseReset();
            Client.clientLogger.info("Register cards set");
        }

    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        sentientClient.getGame().setPhase(phase);
        if(phase == 2 || phase == 3){
            sentientClient.getPlayer().phaseReset();
        }

    }
}
