package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Position;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerTurning extends Message{

    public int clientID;
    public String rotation;

    /**
     * @param clientID
     * @param rotation
     */
    public MessagePlayerTurning(int clientID, String rotation){

        this.clientID = clientID;
        this.rotation = rotation;
        type = "PlayerTurning";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("rotation", new JsonPrimitive(rotation));
        content = jsonObject;
        //Server.serverLogger.info("Created Player Turning Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerTurning(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        rotation = content.get("rotation").getAsString();
        //Server.serverLogger.info("Created Player Turning Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        Position eins;

        if(clientID==client.getId()){
            eins=client.getPlayer().getRobot().getPosition();
            Client.clientLogger.debug("Robot "+client.getPlayer().getRobot().getFigure()+" Rotates  \""+rotation+"\"");
            if (rotation.equals("clockwise")){
                client.getPlayer().getRobot().right();
            }
            else{
                client.getPlayer().getRobot().left();
            }
        }
        else {
            eins=client.playerFromId(clientID).getPlayer().getRobot().getPosition();
                    if (rotation.equals("clockwise")){
                       client.playerFromId(clientID).getPlayer().getRobot().right();
                    }
                    else{
                        client.playerFromId(clientID).getPlayer().getRobot().left();
                    }

        }
        client.getHighSlumber().add(eins);
        client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATEGAMEBOARDPARTS,new TaskContent()));

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID==sentientClient.getId()){
            if (rotation.equals("clockwise")){
                sentientClient.getPlayer().getRobot().right();
            }
            else{
                sentientClient.getPlayer().getRobot().left();
            }
        }
        else{
            if (rotation.equals("clockwise")){
                sentientClient.playerFromId(clientID).getPlayer().getRobot().right();
            }
            else{
                sentientClient.playerFromId(clientID).getPlayer().getRobot().left();
            }
        }
    }
}
