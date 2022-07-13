package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageNameUnavailable extends Message{

    public String name;

    /**
     *
     * @param string
     */
    public MessageNameUnavailable(String string){
        type="NameUnavailable";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("UnavailableName", new JsonPrimitive(string));
        content = jsonObject;
        //Server.serverLogger.info("Created Name Unavailable Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageNameUnavailable(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("UnavailableName").getAsString();
        //Server.serverLogger.info("Created Name Unavailable Message: " + this + " from JSON: " + jsonObject);
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

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
