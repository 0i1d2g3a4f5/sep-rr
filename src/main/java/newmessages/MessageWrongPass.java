package newmessages;

import com.google.gson.JsonObject;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageWrongPass extends Message{
    public MessageWrongPass() {
        type = "WrongPass";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        //Server.serverLogger.info("Created Wrong Pass Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageWrongPass(JsonObject jsonObject){
        super(jsonObject);
        //Server.serverLogger.info("Created Wrong Pass Message: " + this + " from JSON: " + jsonObject);
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
