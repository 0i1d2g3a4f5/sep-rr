package messages;
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
        Server.serverLogger.info("Active Phase: Phase " + phase);
        client.getGame().setPhase(phase);


        if(phase == 2 || phase == 3){
            client.getPlayer().phaseReset();
            Server.serverLogger.info("Register cards set");
        }

    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
