package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Player;
import gamelogic.Position;
import gamelogic.game_elements.ElementName;
import server_package.SClient;
import server_package.Server;
import utility.GlobalParameters;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageSetStartingPoint extends Message{

    public int x;
    public int y;

    /**
     * turn message to json
     *
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
    }

    /**
     * turns json to message
     *
     * @param position
     */
    public MessageSetStartingPoint(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        type = "SetStartingPoint";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageSetStartingPoint(JsonObject jsonObject) {
        super(jsonObject);
        x = content.get("x").getAsInt();
        y = content.get("y").getAsInt();
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        Position position = new Position(y,x);

        if(sClient.getPlayer().getGame().board.getField(position).contains(ElementName.ROBOT)){
            sClient.sendSelf(new MessageError(GlobalParameters.STARTING_POINT_TAKEN_ERROR));
            return;
        }

        if(sClient.getPlayer().placeRobot(position)){
            sClient.sendAll(new MessageStartingPointTaken(x, y, sClient.getId()));
            if (sClient.getPlayer().getGame().getPlayerList().size() - 1 > sClient.getPlayer().getGame().getLastCurrentPlayer()) {
                sClient.getPlayer().getGame().setLastCurrentPlayer(sClient.getPlayer().getGame().getLastCurrentPlayer() + 1);
                sClient.sendAll(new MessageCurrentPlayer(sClient.getPlayer().getGame().getPlayerList().get(sClient.getPlayer().getGame().getLastCurrentPlayer()).getClient().getId()));
            }
        }

        boolean allPlaced = true;
        ArrayList<Player> players = sClient.getPlayer().getGame().getPlayerList();

        for (Player player: players) {
            Server.serverLogger.info("Placed: " +player.getRobot().isPlaced());
            if(!player.getRobot().isPlaced()){
                allPlaced = false;
            }
        }

        if(allPlaced){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        sClient.getPlayer().getGame().startGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    }
            };
            thread.start();
        }
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {}
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
