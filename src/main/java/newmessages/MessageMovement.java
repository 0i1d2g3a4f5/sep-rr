package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageMovement extends Message{

    public int clientID;
    public int x;
    public int y;

    public MessageMovement(int clientID, int x, int y){
        this.clientID = clientID;
        this.x = x;
        this.y = y;
        type = "Movement";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        System.out.println("Created Move Message: " + this);
    }

    public MessageMovement(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        x= content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        System.out.println("Created Move Message: " + this + " from JSON: " + jsonObject);
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
