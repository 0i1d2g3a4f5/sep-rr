package newmessages;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 *
 */

public class MessagePlayerValues extends Message{

    public String name;
    public int figure;

    public MessagePlayerValues(String name, int figure) {
        super(name, figure);
        this.name = name;
        this.figure = figure;
        type = "PlayerValues";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
        //System.out.println("Created SetName Message: " + this);
    }

    public MessagePlayerValues(JsonObject jsonObject){
        super(jsonObject);
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        //System.out.println("Created SetName Message: " + this + " from JSON: " + jsonObject);
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
}
