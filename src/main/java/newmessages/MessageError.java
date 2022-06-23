package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm
 */

public class MessageError extends Message{

    public String error;

    public MessageError(String error){
        super(error);
        this.error = error;
        type = "Error";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", new JsonPrimitive(error));
        content = jsonObject;
        //System.out.println("Created Error Message: " + this);
    }

    public MessageError(JsonObject jsonObject) {
        super(jsonObject);
        error = content.get("error").getAsString();
        //System.out.println("Created Error Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }


    /**
     * @param server
     */

}
