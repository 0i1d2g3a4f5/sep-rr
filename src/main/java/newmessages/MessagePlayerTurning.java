package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerTurning extends Message{

    public int clientID;
    public String rotation;

    public MessagePlayerTurning(int clientID, String rotation){

        this.clientID = clientID;
        this.rotation = rotation;
        type = "PlayerTurning";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clienID", new JsonPrimitive(clientID));
        jsonObject.add("rotation", new JsonPrimitive(rotation));
        content = jsonObject;
        System.out.println("Created Turn Message: " + this);
    }

    public MessagePlayerTurning(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        rotation = content.get("rotation").getAsString();
        System.out.println("Created Turn Message: " + this + " from JSON: " + jsonObject);
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
