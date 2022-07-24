package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageRebootDirection extends Message{

    public String direction;

    /**
     * @param direction
     */
    public MessageRebootDirection(String direction){
        this.direction = direction;
        type = "RebootDirection";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("direction", new JsonPrimitive(direction));
        content = jsonObject;
        //Server.serverLogger.info("Created Reboot Direction Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageRebootDirection(JsonObject jsonObject){
        super(jsonObject);
        direction = content.get("direction").getAsString();
        //Server.serverLogger.info("Created Reboot Direction Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        if(sClient.getPlayer().getRobot().waitingForDirection){
            Direction direction = Direction.parseDirection(this.direction);
            sClient.getPlayer().getRobot().setDirectionFacing(direction);
            Server.serverLogger.info("Rebooted Robot "+sClient.getFigure()+" wit Direction "+direction.toString());
        }

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
