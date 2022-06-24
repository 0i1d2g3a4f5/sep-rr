package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */
public class MessageHelloServer extends Message {

    public String group;
    public boolean isAI;
    public String protocol;

    public MessageHelloServer(String group, boolean isAI, String protocol) {
        this.group = group;
        this.isAI = isAI;
        this.protocol = protocol;
        type = "HelloServer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("group", new JsonPrimitive(group));
        jsonObject.add("isAI", new JsonPrimitive(isAI));
        jsonObject.add("protocol", new JsonPrimitive(protocol));
        content = jsonObject;
        //System.out.println("Created GroupIdentification Message: " + this);
    }

    public MessageHelloServer(JsonObject jsonObject) {
        super(jsonObject);
        group = content.get("group").getAsString();
        isAI = content.get("isAI").getAsBoolean();
        protocol = content.get("protocol").getAsString();
        //System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (!this.protocol.equals("Version 0.1")) {
                client.sendSelf(new MessageError("ERROR :: False communication protocol."));
            } else {
                client.setAI(this.isAI);
                client.setGroup(this.group);
                client.sendSelf(new MessageWelcome(client.getId()));
            }
        }
        else{
            //ADVANCED
        }


    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }


}
