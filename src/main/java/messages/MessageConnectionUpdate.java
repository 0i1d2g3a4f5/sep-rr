package messages;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import server_package.SClient;

import java.io.IOException;

/**
 * @author Qinyi
 * update the status the client, leaved or rejoining
 */
public class MessageConnectionUpdate extends Message{
    public int clientID;

    public boolean isConnected;

    public String action;



    /**
     * converts message to json
     *
     * @param clientID
     * @param isConnected
     * @param action
     */
    public MessageConnectionUpdate(int clientID, boolean isConnected, String action){
        this.clientID = clientID;
        this.isConnected = isConnected;
        this.action = action;
        type = "ConnectionUpdate";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID",new JsonPrimitive(clientID));
        jsonObject.add("isConnected",new JsonPrimitive(isConnected));
        jsonObject.add("action",new JsonPrimitive(action));
        content = jsonObject;

    }

    public MessageConnectionUpdate(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        isConnected = content.get("isConnected").getAsBoolean();
        action = content.get("action").getAsString();
        //Server.serverLogger.info("Created ConnectionUpdate Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient client) throws IOException, ClientNotFoundException{
        for(int i=0; i<client.getServer().getClientList().size(); i++){
            if(client.getServer().getClientList().get(i).getId()==clientID){
                client.getServer().getClientList().get(i).disconnect();
            }
        }
        client.sendAll(new MessageConnectionUpdate(clientID, false, "Disconnect"));

    }

    /**
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        for(int i=0; i<client.getClientList().size(); i++){
            if (client.getClientList().get(i).getId() == clientID) {
                client.getClientList().remove(i);
            }
        }
        for(int i=0; i<client.getPlayerList().size(); i++){
            if (client.getPlayerList().get(i).getId() == clientID) {
                client.getPlayerList().remove(i);
            }
        }

    }

    /**
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        for(int i=0; i<sentientClient.getClientList().size(); i++){
            if (sentientClient.getClientList().get(i).getId() == clientID) {
                sentientClient.getClientList().remove(i);
            }
        }
        for(int i=0; i<sentientClient.getPlayerList().size(); i++){
            if (sentientClient.getPlayerList().get(i).getId() == clientID) {
                sentientClient.getPlayerList().remove(i);
            }
        }
    }
}
