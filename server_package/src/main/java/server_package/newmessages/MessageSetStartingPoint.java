package server_package.newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.gamelogic.Position;
import server_package.Client;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageSetStartingPoint extends Message{

    public int x;
    public int y;

    /**
     * @param x
     * @param y
     */
    public MessageSetStartingPoint(int x, int y) {
        this.x = x;
        this.y = y;
        type = "SetStartingPoint";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        System.out.println("Created StartRequest Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSetStartingPoint(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        System.out.println("Created StartRequest Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

        Position position = new Position(y,x);
        if(client.getPlayer().placeRobot(position)){
            client.sendAll(new MessageStartingPointTaken(x,y, client.getId()));
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
}
