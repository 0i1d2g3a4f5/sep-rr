package newmessages;

import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageAlive extends Message{

    public MessageAlive() {
        super();
        type = "Alive";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        //System.out.println("Created ALive Message: " + this);
    }

    public MessageAlive(JsonObject jsonObject){
        super(jsonObject);
        //System.out.println("Created Alive Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }
}
