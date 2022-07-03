package client_package.clientSideMessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageFigureUnavailable extends Message {
    public int figure;
    public MessageFigureUnavailable(int i){
        this.
        type="FigureUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Figure", new JsonPrimitive(i));
        content = jsonObject;
    }
    public MessageFigureUnavailable(JsonObject jsonObject){
        super(jsonObject);
        figure=content.get("Figure").getAsInt();
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
