package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardFactory;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Vivian Kafadar
 */

public class MessagePickDamage extends Message {

    public int remainingToDraw;
    private ArrayList<String> availablePiles;
    private JsonObject jsonObject;

    /**
     * converts message to json
     *
     * @param remainingToDraw
     * @param availablePiles
     * @author Isabel Muhm
     */
    public MessagePickDamage(int remainingToDraw, ArrayList<String> availablePiles) {
        this.remainingToDraw = remainingToDraw;
        type = "PickDamage";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("remainingToDraw", new JsonPrimitive(remainingToDraw));
        content = jsonObject;
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < availablePiles.size(); i++) {
            JsonObject infoJObject = new JsonObject();
            infoJObject.add("availablePiles", new JsonPrimitive(availablePiles.get(i).toString()));
            jsonArray.add(infoJObject);
        }
        jsonObject.add("availablePiles",jsonArray);
        content = jsonObject;
        //Server.serverLogger.info("Created Pick Damage Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @throws IOException
     * @author Isabel Muhm
     */
    public void MessagePickDamage(JsonObject jsonObject) throws IOException {
        JsonArray jsonArray = jsonObject.get("availablePiles").getAsJsonArray();
        ArrayList<String> availablePiles = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        for (int i = 0; i < availablePiles.size(); i++) {
        }
        this.availablePiles = availablePiles;
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
