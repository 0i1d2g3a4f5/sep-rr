package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageRebootDirection extends Message{

    public String direction;

    /**
     * @param direction
     */
    public MessageRebootDirection(String direction){
        this.direction = direction;
        type = "RebootDirection";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("direction", new JsonPrimitive(direction));
        content = jsonObject;
        System.out.println("Created RebootDirection Message: " + this);
        Server.serverLogger.info("Created Reboot Direction Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageRebootDirection(JsonObject jsonObject){
        super(jsonObject);
        direction = content.get("direction").getAsString();
        System.out.println("Created RebootDirection Message: " + this + " from JSON: " + jsonObject);
        Server.serverLogger.info("Created Reboot Direction Message: " + this + " from JSON: " + jsonObject);
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
