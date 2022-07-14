package newmessages;


import client_package.client_gamelogic.OtherClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerValues extends Message{

    public String name;
    public int figure;

    /**
     * @param name
     * @param figure
     */
    public MessagePlayerValues(String name, int figure) {
        this.name = name;
        this.figure = figure;
        type = "PlayerValues";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
        //Server.serverLogger.info("Created Player Values Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerValues(JsonObject jsonObject){
        super(jsonObject);
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        //Server.serverLogger.info("Created Player Values Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            sClient.checkValues(this.name, this.figure);
        }
        else {
            //ADVANCED
        }
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

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        
    }
}
