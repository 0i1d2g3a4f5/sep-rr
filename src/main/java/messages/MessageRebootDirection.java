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
     * converts message to json
     *
     * @param direction
     */
    public MessageRebootDirection(String direction){
        this.direction = direction;
        type = "RebootDirection";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("direction", new JsonPrimitive(direction));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     */
    public MessageRebootDirection(JsonObject jsonObject){
        super(jsonObject);
        direction = content.get("direction").getAsString();
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        Server.serverLogger.debug("RebootDirection");
            Direction direction = Direction.parseDirection(this.direction);
            sClient.getPlayer().getRobot().rotateTo(direction);
            Server.serverLogger.info("Rebooted Robot "+sClient.getFigure()+" wit Direction "+direction.toString());
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {}

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
