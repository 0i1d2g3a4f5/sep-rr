package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Player;
import gamelogic.Position;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;


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
        //Server.serverLogger.info("Created Set Starting Point Message: " + this);
    }
    public MessageSetStartingPoint(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        type = "SetStartingPoint";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
        //Server.serverLogger.info("Created Set Starting Point Message: " + this + " from JSON: " + jsonObject);
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
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        //TODO insert
        Position position = new Position(y,x);
        if(sClient.getPlayer().placeRobot(position)){
            sClient.sendAll(new MessageStartingPointTaken(x,y, sClient.getId()));
        }

       // sClient.sendSelf(new MessageSendChat("Server: checking if you already placed your Robot", sClient.getId()));
        boolean allPlaced = true;

        ArrayList<Player> players = sClient.getPlayer().getGame().getPlayerList();

        for (Player player: players) {
            player.sendMessage(new MessageSendChat("Server: checking if you already placed your Robot", sClient.getId()));
            System.out.println("placed: " +player.getRobot().isPlaced());
            if(!player.getRobot().isPlaced()){
                allPlaced = false;
            }
        }

        sClient.sendSelf(new MessageSendChat("Server: all placed "+ allPlaced, sClient.getId()));

        if(allPlaced){


                sClient.sendSelf(new MessageSendChat("Server: Initializing Game", sClient.getId()));
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
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
    }
}
