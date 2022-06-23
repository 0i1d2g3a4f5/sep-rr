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
        super(card);
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
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }
}
