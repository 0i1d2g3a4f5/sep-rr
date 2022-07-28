package messages;

import client_package.sentient.SentientClient;
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
     * converts message to json
     *
     * @param group
     * @param isAI
     * @param protocol
     * @author Isabel Muhm
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
        //Server.serverLogger.info("Created Hello Server Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageHelloServer(JsonObject jsonObject) {
        super(jsonObject);
        group = content.get("group").getAsString();
        isAI = content.get("isAI").getAsBoolean();
        protocol = content.get("protocol").getAsString();
        //Server.serverLogger.info("Created Hello Server Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * checks if servers protocol version equals to clients protocol version and sends welcome message to client
     *
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
            if (!this.protocol.equals(PROTOCOL_VERSION)) {
                sClient.sendSelf(new MessageError("ERROR :: False communication protocol."));
            } else {
                sClient.setAI(this.isAI);
                sClient.setGroup(this.group);
                sClient.sendSelf(new MessageWelcome(sClient.getId()));

                    Server.serverLogger.info("SClient "+ ", with ID: " + sClient.getId() + ", joined group " + this.group + " with protocol version " + this.protocol + ".");

            }



    }

    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }


}
