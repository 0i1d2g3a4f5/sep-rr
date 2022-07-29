package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageValuesAccepted extends Message{

    public String name;
    public int figure;

    /**
     * converts message to json
     *
     * @param name
     * @param figure
     * @author Isabel Muhm
     */
    public MessageValuesAccepted(String name, int figure){
        type="ValuesAccepted";
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Figure", new JsonPrimitive(figure));
        content=jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageValuesAccepted(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
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
