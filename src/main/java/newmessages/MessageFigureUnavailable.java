package newmessages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageFigureUnavailable extends Message{
    public int figure;
    public MessageFigureUnavailable(int i){
        this.
        type="FigureUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Figure", new JsonPrimitive(i));
        content = jsonObject;
        //Server.serverLogger.info("Created Figure Unavailable Message: " + this);
    }
    public MessageFigureUnavailable(JsonObject jsonObject){
        super(jsonObject);
        figure=content.get("Figure").getAsInt();
        //Server.serverLogger.info("Created Figure Unavailable Message: " + this + " from JSON: " + jsonObject);
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

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }

}
