package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageValuesAccepted extends Message{
    public String name;
    public int figure;
    public MessageValuesAccepted(String name, int figure){
        type="ValuesAccepted";
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Figure", new JsonPrimitive(figure));
        content=jsonObject;

    }
    public MessageValuesAccepted(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
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

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

}
