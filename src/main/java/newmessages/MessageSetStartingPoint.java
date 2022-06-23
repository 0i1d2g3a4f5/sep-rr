package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Position;
import server_package.Client;

import java.io.IOException;


/**
 * @author Isabel Muhm
 */

public class MessageSetStartingPoint extends Message{

    public int x;
    public int y;

    public MessageSetStartingPoint(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        type = "SetStartingPoint";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        System.out.println("Created StartRequest Message: " + this);
    }

    public MessageSetStartingPoint(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x").getAsInt();
        y = content.get("y").getAsInt();
        System.out.println("Created StartRequest Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

        Position position = new Position(y,x);
        if(client.getPlayer().placeRobot(position)){
            client.sendAll(new MessageStartingPointTaken(x,y, client.getId()));
        }

    }
}
