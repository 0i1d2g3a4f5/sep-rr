package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHelloServer extends Message {

    public String group;
    public boolean isAI;
    public String protocol;

    /**
     * @param group
     * @param isAI
     * @param protocol
     */
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
        Server.serverLogger.info("Created Hello Server Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageHelloServer(JsonObject jsonObject) {
        super(jsonObject);
        group = content.get("group").getAsString();
        isAI = content.get("isAI").getAsBoolean();
        protocol = content.get("protocol").getAsString();
        //System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
        Server.serverLogger.info("Created Hello Server Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (!this.protocol.equals(PROTOCOL_VERSION)) {
                sClient.sendSelf(new MessageError("ERROR :: False communication protocol."));
            } else {
                sClient.setAI(this.isAI);
                sClient.setGroup(this.group);
                sClient.sendSelf(new MessageWelcome(sClient.getId()));

                    System.out.println("SClient "+ ", with ID: " + sClient.getId() + ", joined group " + this.group + " with protocol version " + this.protocol + ".");
                    Server.serverLogger.info("SClient "+ ", with ID: " + sClient.getId() + ", joined group " + this.group + " with protocol version " + this.protocol + ".");

            }
        }
        else{
            //ADVANCED
        }


    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }



}
