package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessagePlayCard extends Message{

    public String card;

    /**
     * @param card
     */
    public MessagePlayCard(String card){
        this.card = card;
        type = "PlayCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        System.out.println("Created PlayCard Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayCard(JsonObject jsonObject){
        super(jsonObject);
        card = content.get("card").getAsString();
        System.out.println("Created PlayCard Message: " + this + " from JSON: " + jsonObject);
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
