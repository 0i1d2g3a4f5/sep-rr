package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageFigureUnavailable extends Message{
    public int figure;

    /**
     * converts message to json
     * @param i
     */
    public MessageFigureUnavailable(int i){
        this.
        type="FigureUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Figure", new JsonPrimitive(i));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel
     */
    public MessageFigureUnavailable(JsonObject jsonObject){
        super(jsonObject);
        figure=content.get("Figure").getAsInt();
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
