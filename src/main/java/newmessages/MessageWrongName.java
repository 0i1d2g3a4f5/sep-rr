package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWrongName extends Message{

    public String name;

    /**
     * @param string
     */
    public MessageWrongName(String string) {
        type = "WrongName";
        this.name=string;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        content = jsonObject;
        //Server.serverLogger.info("Created Wrong Name Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageWrongName(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        //Server.serverLogger.info("Created Wrong Name Message: " + this + " from JSON: " + jsonObject);
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
