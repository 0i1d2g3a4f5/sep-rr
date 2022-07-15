package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHelloClient extends Message{
    public String protocol;

    /**
     * @param string
     */
    public MessageHelloClient(String string){
        protocol=string;
        type="HelloClient";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("protocol", new JsonPrimitive(string));
        content=jsonObject;
        //Server.serverLogger.info("Created Hello Client Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageHelloClient(JsonObject jsonObject){
        super(jsonObject);
        protocol=content.get("protocol").getAsString();
        //Server.serverLogger.info("Created Hello Client Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.protocol.equals(PROTOCOL_VERSION)) {
                Server.serverLogger.info("Correct communication protocol verified");
            } else {
                Server.serverLogger.error("False communication protocol");
            }
            // TODO do not compare global parameter "protocol_version" with itself, instead let server tell client its version
        }
        else{
            //ADVANCED
        }
    }
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.protocol.equals(client.getAiController().protocolVersion)) {
                Server.serverLogger.info("Correct communication protocol verified");
                client.sayHello(client.getAiController().groupName, client.getAiController().protocolVersion);
            } else {
                Server.serverLogger.error("False communication protocol");
            }
        }
        else{
            //ADVANCED
        }
    }

}
