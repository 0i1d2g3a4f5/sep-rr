package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessagePlayCard extends Message{

    public String card;

    public MessagePlayCard(String card){
        this.card = card;
        type = "PlayCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        System.out.println("Created PlayCard Message: " + this);
    }

    public MessagePlayCard(JsonObject jsonObject){
        super(jsonObject);
        card = content.get("card").getAsString();
        System.out.println("Created PlayCard Message: " + this + " from JSON: " + jsonObject);
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

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
