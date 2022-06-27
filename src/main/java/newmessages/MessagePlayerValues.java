package newmessages;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerValues extends Message{

    public String name;
    public int figure;

    public MessagePlayerValues(String name, int figure) {
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
    public void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            client.checkValues(this.name, this.figure);
        }
        else {
            //ADVANCED
        }
    }
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        
    }
}
