package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.map.MapName;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageMapSelected extends Message{

    public String map;

    public MessageMapSelected(String map) {
        super(map);
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
        System.out.println("Created MapSelected Message: " + this);
    }

    public MessageMapSelected(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
        System.out.println("Created MapSelected Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     * @uthor Ringer
     */

    @Override
    public void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            try {
                client.getServer().getGame().setMapName(MapName.valueOf(map));
                for (Client clientSend:client.getServer().getClientList()) {
                    clientSend.sendSelf(this);
                }
            } catch (IllegalArgumentException e){
                //client.sendSelf(new MessageSelectMap());
            }
        }
        else {
            //ADVANCED
        }
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
