package newmessages;

import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */

public class MessageWrongPass extends Message{
    public MessageWrongPass() {
        super();
        type = "WrongPass";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
    }

    public MessageWrongPass(JsonObject jsonObject){
        super(jsonObject);
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
