package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageCardSelected extends Message{

    public int clientID;
    public int register;
    public boolean filled;

    public MessageCardSelected(int clientID, int register, boolean filled){
        this.clientID =clientID;
        this.register = register;
        this.filled= filled;
        type = "CardSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("register", new JsonPrimitive(register));
        jsonObject.add("filled", new JsonPrimitive(filled));
        content = jsonObject;
        System.out.println("Created Register Message: " + this);
    }

    public MessageCardSelected(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        register = content.get("register").getAsInt();
        filled = content.get("filled").getAsBoolean();
        System.out.println("Created Register Message: " + this + " from JSON: " + jsonObject);
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
